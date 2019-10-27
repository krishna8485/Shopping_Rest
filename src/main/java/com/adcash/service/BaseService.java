package com.adcash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adcash.entities.Category;
import com.adcash.entities.Product;
import com.adcash.repository.CategoryRepository;
import com.adcash.repository.ProductRepository;

@Service("baseService")
//TODO : Still need to fine tune the @Transactional
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
public class BaseService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Optional<Category> findById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}
	
	public Optional<Category> updateById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}
	
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
	
	public Category save(Category category) {
		return categoryRepository.save(category);		
	}
	
	public List<Product> findByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);		
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public boolean existsByCategoryId(Long categoryId) {
		return categoryRepository.existsById(categoryId);
	}
	
	public Optional<Product> findByIdProduct(Long productId) {		
		return productRepository.findById(productId);
	}
	
	public Optional<Product> findByIdAndCategoryId(Long productId, Long categoryId){
		return productRepository.findByIdAndCategoryId(productId, categoryId);
	}
	
	public void delete(Product product) {		
		productRepository.delete(product);
	}

}
