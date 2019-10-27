package com.adcash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adcash.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
 List<Product> findByCategoryId(Long categoryId);
 Optional<Product> findByIdAndCategoryId(Long id, Long categoryId);
}
