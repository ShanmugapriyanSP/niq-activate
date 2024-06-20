package com.niqactivate.ecommerce.service;

import com.niqactivate.ecommerce.dto.ProductFilterDto;
import com.niqactivate.ecommerce.dto.ProductMetadataRequestDto;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.model.ShopperProduct;
import com.niqactivate.ecommerce.repository.ProductRepository;
import com.niqactivate.ecommerce.repository.ShopperProductRepository;
import com.niqactivate.ecommerce.specification.ProductSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopperProductRepository shopperProductRepository;

    public Product saveProduct(ProductMetadataRequestDto productRequestDto) {
        Product product = Product.builder()
                .productId(productRequestDto.getProductId())
                .brand(productRequestDto.getBrand())
                .category(productRequestDto.getCategory())
                .build();
        return productRepository.save(product);
    }

    public Product getProduct(String productId) {
        return productRepository.findByProductId(productId);
    }

    public List<Product> findProductsByShopper(ProductFilterDto filterDto) {
        int limit = filterDto.getLimit();
        int effectiveLimit = (limit > 0 && limit <= 100) ? limit : 10;
        Pageable pageable = PageRequest.of(0, effectiveLimit, Sort.by(Sort.Direction.DESC, "relevancyScore"));

        Specification<ShopperProduct> spec = Specification.where(ProductSpecifications.hasShopperId(filterDto.getShopperId()))
                .and(ProductSpecifications.hasCategory(filterDto.getCategory()))
                .and(ProductSpecifications.hasBrand(filterDto.getBrand()));

        log.info("Fetching products for shopper ID - {}", filterDto.getShopperId());

        return shopperProductRepository.findAll(spec, pageable).map(ShopperProduct::getProduct).getContent();
    }
}
