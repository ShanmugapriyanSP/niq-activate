package com.niqactivate.ecommerce.dto;

import com.niqactivate.ecommerce.model.ShopperProduct;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ShopperProductResponseDto {

    private String shopperId;
    private Set<ShopperProduct> shopperProduct;
}
