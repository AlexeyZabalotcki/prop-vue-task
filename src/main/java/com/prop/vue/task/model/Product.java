package com.prop.vue.task.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@ToString
@Table(name = "products")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productsSeqGenerator")
    @SequenceGenerator(name = "productsSeqGenerator", sequenceName = "products_seq", allocationSize = 1)
    private Long id;

    private String productId;
    private String status;
    private String fulfillmentCenter;
    private int quantity;
    private double value;
}