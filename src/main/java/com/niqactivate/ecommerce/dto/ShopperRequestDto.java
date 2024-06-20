package com.niqactivate.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopperRequestDto {

    private String shopperId;
    private List<ProductShelfItemDto> shelf;
}
