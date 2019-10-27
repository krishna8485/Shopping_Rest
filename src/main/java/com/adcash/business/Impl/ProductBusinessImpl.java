package com.adcash.business.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adcash.entities.Product;
import com.adcash.exception.ResourceNotFoundException;
import com.adcash.model.request.ProductRequest;
import com.adcash.model.response.ProductResponse;
import com.adcash.service.BaseService;

@Component("productBusinessImpl")
public class ProductBusinessImpl {
	
	@Autowired
	BaseService baseService;
	
	private static final Logger logger = LogManager.getLogger(ProductBusinessImpl.class);

	public List<ProductResponse> findProducts(Long categoryId) throws ResourceNotFoundException {
		List<ProductResponse>  productResponses = new ArrayList<>();
		baseService.findByCategoryId(categoryId).forEach(product ->{ 
			logger.info("product --> " + product.toString());
			productResponses.add(new ProductResponse(product.getId(), product.getPname()));			
		});
		if(productResponses==null || productResponses.isEmpty()) {
			throw new ResourceNotFoundException("No Data Found");
		}
		return productResponses;
	}

	public ProductResponse createProduct(Long categoryId, ProductRequest productRequest) throws ResourceNotFoundException{		
		Product productOuter = baseService.findById(categoryId).map(category -> {
			logger.info("category --> " + category.toString());
			Product product = new Product();
			product.setCategory(category);
			product.setPname(productRequest.getName());
			logger.info("product --> " + product.toString());
			return baseService.saveProduct(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		return new ProductResponse(productOuter.getId(), productOuter.getPname());
	}

	public ProductResponse updateProduct(Long categoryId, Long productId, ProductRequest productRequest) throws ResourceNotFoundException {
		if (!baseService.existsByCategoryId(categoryId)) {
            throw new ResourceNotFoundException("category id not found");
        }
         Product productOuter = baseService.findByIdProduct(productId).map(product -> {        	 
			product.setPname(productRequest.getName());
			logger.info("product --> " + product.toString());
            return baseService.saveProduct(product);
        }).orElseThrow(() -> new ResourceNotFoundException("product id not found"));
         return new ProductResponse(productOuter.getId(), productOuter.getPname());
	}

	public void deleteProduct(Long categoryId, Long productId) throws ResourceNotFoundException{
			baseService.findByIdAndCategoryId(productId, categoryId).map(product -> {
			 logger.info("product --> " + product.toString());
			 baseService.delete(product);
			 return product;
	        }).orElseThrow(() -> new ResourceNotFoundException(
	            "Product not found for both id " + productId + " and categoryId " + categoryId));		
	}
}
