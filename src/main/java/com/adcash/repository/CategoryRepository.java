package com.adcash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adcash.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
