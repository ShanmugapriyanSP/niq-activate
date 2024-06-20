package com.niqactivate.ecommerce.controller;

import com.niqactivate.ecommerce.dto.ProductFilterDto;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
public class ExternalController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsByShopper(@RequestParam String shopperId,
                                                              @RequestParam(required = false) String category,
                                                              @RequestParam(required = false) String brand,
                                                              @RequestParam(defaultValue = "10") Integer limit) {
        ProductFilterDto filterDto = ProductFilterDto.builder()
                .shopperId(shopperId)
                .category(category)
                .brand(brand)
                .limit(limit)
                .build();

        List<Product> products = productService.findProductsByShopper(filterDto);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
