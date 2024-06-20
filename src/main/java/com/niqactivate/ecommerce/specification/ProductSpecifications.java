package com.niqactivate.ecommerce.specification;

import com.niqactivate.ecommerce.model.ShopperProduct;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<ShopperProduct> hasShopperId(String shopperId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shopper").get("shopperId"), shopperId);
    }

    public static Specification<ShopperProduct> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("product").get("category"), category);
    }

    public static Specification<ShopperProduct> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                brand == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("product").get("brand"), brand);
    }
}

