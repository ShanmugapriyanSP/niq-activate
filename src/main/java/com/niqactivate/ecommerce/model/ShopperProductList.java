package com.niqactivate.ecommerce.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ShopperProductList {
    @Id
    private String shopperId;

    @ElementCollection
    private List<Product> shelf;

    @Embeddable
    @Data
    public static class Product {
        private String productId;
        private double relevancyScore;
    }
}