package com.niqactivate.ecommerce.controller;

import com.niqactivate.ecommerce.dto.ProductRequestDto;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.service.PersonalizedDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
@Validated
@RequiredArgsConstructor
public class PersonalizedDataController {

    private PersonalizedDataService personalizedDataService;

    @GetMapping("/shopper-product-list")
    public List<Product> getProducts(@Valid ProductRequestDto productRequestDto) {
        return personalizedDataService.getProducts(productRequestDto);
    }
}
