package com.niqactivate.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull(message = "Shopper ID is required")
    private String shopperId;
    private String category;
    private String brand;
    private Integer limit = 10;
}
