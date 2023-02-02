package com.example2.toturial.repositories;

import com.example2.toturial.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProductName(String productName);
}
