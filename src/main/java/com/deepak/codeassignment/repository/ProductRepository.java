package com.deepak.codeassignment.repository;

import com.deepak.codeassignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
