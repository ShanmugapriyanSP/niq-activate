package com.niqactivate.ecommerce.repository;

import com.niqactivate.ecommerce.model.ShopperProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long>, JpaSpecificationExecutor<ShopperProduct> {
}
