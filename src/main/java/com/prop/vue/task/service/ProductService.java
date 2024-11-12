package com.prop.vue.task.service;

import com.prop.vue.task.dto.ProductDto;
import com.prop.vue.task.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByStatus(String status);

    ProductDto createProduct(ProductDto productDTO);

    ProductDto updateProduct(Long id, ProductDto productDetails);

    void deleteProduct(Long id);

    double getTotalValueByStatus(String status);

    double getTotalValueByFulfillmentCenter(String fulfillmentCenter);
}
