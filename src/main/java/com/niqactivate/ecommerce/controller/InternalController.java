package com.niqactivate.ecommerce.controller;

import com.niqactivate.ecommerce.dto.ProductMetadataRequestDto;
import com.niqactivate.ecommerce.dto.ShopperProductResponseDto;
import com.niqactivate.ecommerce.dto.ShopperRequestDto;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.service.ProductService;
import com.niqactivate.ecommerce.service.ShopperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InternalController {

    private final ProductService productService;
    private final ShopperService shopperService;

    @PostMapping("/shopper")
    public ResponseEntity<ShopperProductResponseDto> saveShopper(@RequestBody ShopperRequestDto shopperRequestDto) throws Exception{
        ShopperProductResponseDto savedShopper = shopperService.saveShopperPersonalizedList(shopperRequestDto);
        return new ResponseEntity<>(savedShopper, HttpStatus.CREATED);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductMetadataRequestDto productRequestDto) {
        Product savedProduct = productService.saveProduct(productRequestDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
