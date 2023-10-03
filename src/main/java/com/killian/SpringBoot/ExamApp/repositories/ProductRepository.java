package com.killian.SpringBoot.ExamApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killian.SpringBoot.ExamApp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
