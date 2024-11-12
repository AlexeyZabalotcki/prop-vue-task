package com.prop.vue.task.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String productId;
    private String status;
    private String fulfillmentCenter;
    private int quantity;
    private double value;
}