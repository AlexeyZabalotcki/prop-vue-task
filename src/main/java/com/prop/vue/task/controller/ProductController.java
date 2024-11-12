package com.prop.vue.task.controller;

import com.prop.vue.task.dto.ProductDto;
import com.prop.vue.task.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Get all products or filter by status
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(required = false) String status) {
        List<ProductDto> products;
        if (status != null) {
            products = productService.getProductsByStatus(status);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
        ProductDto createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDetails) {
        ProductDto updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Get total value by status
    @GetMapping("/total-value/status/{status}")
    public ResponseEntity<Double> getTotalValueByStatus(@PathVariable String status) {
        double totalValue = productService.getTotalValueByStatus(status);
        return ResponseEntity.ok(totalValue);
    }

    // Get total value by fulfillment center
    @GetMapping("/total-value/fulfillment-center/{center}")
    public ResponseEntity<Double> getTotalValueByFulfillmentCenter(@PathVariable String center) {
        double totalValue = productService.getTotalValueByFulfillmentCenter(center);
        return ResponseEntity.ok(totalValue);
    }
}
