package com.niqactivate.ecommerce.repository;

import com.niqactivate.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
