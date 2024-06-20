package com.niqactivate.ecommerce.repository;

import com.niqactivate.ecommerce.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    Shopper findByShopperId(String shopperId);
}
