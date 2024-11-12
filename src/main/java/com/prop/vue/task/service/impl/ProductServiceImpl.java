package com.prop.vue.task.service.impl;

import com.prop.vue.task.dao.ProductRepository;
import com.prop.vue.task.dto.ProductDto;
import com.prop.vue.task.model.Product;
import com.prop.vue.task.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private ProductDto convertToDTO(Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductId(),
                product.getStatus(),
                product.getFulfillmentCenter(),
                product.getQuantity(),
                product.getValue()
        );
    }

    private Product convertToEntity(ProductDto dto) {
        return new Product(
                dto.getId(),
                dto.getProductId(),
                dto.getStatus(),
                dto.getFulfillmentCenter(),
                dto.getQuantity(),
                dto.getValue()
        );
    }


    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByStatus(String status) {
        return productRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDetails) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductId(productDetails.getProductId());
            existingProduct.setStatus(productDetails.getStatus());
            existingProduct.setFulfillmentCenter(productDetails.getFulfillmentCenter());
            existingProduct.setQuantity(productDetails.getQuantity());
            existingProduct.setValue(productDetails.getValue());
            Product updatedProduct = productRepository.save(existingProduct);
            return convertToDTO(updatedProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public double getTotalValueByStatus(String status) {
        return productRepository.findByStatus(status)
                .stream()
                .mapToDouble(Product::getValue)
                .sum();
    }

    @Override
    public double getTotalValueByFulfillmentCenter(String fulfillmentCenter) {
        return productRepository.findByFulfillmentCenter(fulfillmentCenter)
                .stream()
                .mapToDouble(Product::getValue)
                .sum();
    }
}
