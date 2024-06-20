package com.niqactivate.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFilterDto {
    private String shopperId;
    private String category;
    private String brand;
    private Integer limit = 10; // default limit
}