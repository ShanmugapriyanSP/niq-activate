package com.niqactivate.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopper_products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShopperProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double relevancyScore;

}

