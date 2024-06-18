package com.niqactivate.ecommerce.service;

import com.niqactivate.ecommerce.dto.ProductRequestDto;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.repository.ShopperProductListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalizedDataService {

    private ShopperProductListRepository shopperProductListRepository;

    public List<Product> getProducts(ProductRequestDto productRequestDto) {
        return null;
    }
}
