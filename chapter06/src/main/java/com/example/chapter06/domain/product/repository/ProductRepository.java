package com.example.chapter06.domain.product.repository;

import com.example.chapter06.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> { }
