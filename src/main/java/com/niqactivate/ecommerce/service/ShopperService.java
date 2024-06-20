package com.niqactivate.ecommerce.service;


import com.niqactivate.ecommerce.dto.ProductShelfItemDto;
import com.niqactivate.ecommerce.dto.ShopperProductResponseDto;
import com.niqactivate.ecommerce.dto.ShopperRequestDto;
import com.niqactivate.ecommerce.exception.ProductNotFoundException;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.model.Shopper;
import com.niqactivate.ecommerce.model.ShopperProduct;
import com.niqactivate.ecommerce.repository.ShopperProductRepository;
import com.niqactivate.ecommerce.repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShopperService {

    private final ShopperRepository shopperRepository;
    private final ShopperProductRepository shopperProductRepository;
    private final ProductService productService;

    public ShopperProductResponseDto saveShopperPersonalizedList(ShopperRequestDto shopperRequestDto) throws ProductNotFoundException {
        Shopper shopper = shopperRepository.findByShopperId(shopperRequestDto.getShopperId());
        if (shopper == null) {
            shopper = Shopper.builder()
                    .shopperId(shopperRequestDto.getShopperId())
                    .build();
            log.info("Creating a new shopper --> {}", shopper.getShopperId());
            shopper = shopperRepository.save(shopper);
        }

        Set<ShopperProduct> shopperProducts = new HashSet<>();

        for(ProductShelfItemDto productShelfItemDto: shopperRequestDto.getShelf()) {
            Product product = productService.getProduct(productShelfItemDto.getProductId());

            if (product == null) {
                log.error("ProductID not found --> {}", productShelfItemDto.getProductId());
                throw new ProductNotFoundException("ProductID not found --> " + productShelfItemDto.getProductId());
            }

            ShopperProduct shopperProduct = ShopperProduct.builder()
                            .shopper(shopper)
                            .product(product)
                            .relevancyScore(productShelfItemDto.getRelevancyScore())
                            .build();
            shopperProducts.add(shopperProduct);
        }

        shopperProducts.forEach(shopperProductRepository::save);

        log.info("Shopper products created !!!");
        return ShopperProductResponseDto.builder()
                .shopperId(shopper.getShopperId())
                .shopperProduct(shopperProducts)
                .build();
    }
}
