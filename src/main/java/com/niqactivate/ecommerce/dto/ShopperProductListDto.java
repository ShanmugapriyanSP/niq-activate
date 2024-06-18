package com.niqactivate.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopperProductListDto {

    private String shopperId;
    private List<Product> shelf;

    @Data
    public static class Product {
        private String productId;
        private double relevancyScore;
    }
}
