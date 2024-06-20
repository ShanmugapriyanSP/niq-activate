package com.niqactivate.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetadataRequestDto {
    private String productId;
    private String category;
    private String brand;
}
