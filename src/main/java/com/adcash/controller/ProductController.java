package com.adcash.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adcash.business.Impl.ProductBusinessImpl;
import com.adcash.exception.ResourceNotFoundException;
import com.adcash.model.request.ProductRequest;
import com.adcash.model.response.ProductResponse;
import com.adcash.model.response.Success;

@RestController
@Validated
public class ProductController {
	
	 	@Autowired @Qualifier ("productBusinessImpl")
	    private ProductBusinessImpl productBusinessImpl; 	

	 	private static final Logger logger = LogManager.getLogger(ProductController.class);

	    @GetMapping(value="/categories/{categoryId}/products", produces= {"application/json", "application/xml"})
	    public ResponseEntity<List <ProductResponse>> getProductsByCategory(@PathVariable(value = "categoryId") @Min(1) Long categoryId) throws ResourceNotFoundException {
	    	logger.info("categoryId --> "+categoryId);
	    	List <ProductResponse> productResponses =  productBusinessImpl.findProducts(categoryId);
	    	logger.info("productResponses --> "+productResponses.toString());
	    	return new ResponseEntity<>(productResponses, HttpStatus.OK);
	    }

	    @PostMapping(value="/category/{categoryId}/product" , produces= {"application/json", "application/xml"}, consumes= {"application/json", "application/xml"})
	    public ResponseEntity<ProductResponse> createProduct(@PathVariable(value = "categoryId") @Min(1)Long categoryId,
	        @Valid @RequestBody ProductRequest productRequest) throws ResourceNotFoundException {
	    	logger.info("categoryId --> "+categoryId +"productRequest -->"+productRequest.toString());
	    	ProductResponse productResponse = productBusinessImpl.createProduct(categoryId, productRequest);
	    	logger.info("productResponse"+productResponse.toString());
	    	return new ResponseEntity<>(productResponse, HttpStatus.CREATED);	        
	    }

	    @PutMapping(value="/category/{categoryId}/product/{productId}" , produces= {"application/json", "application/xml"}, consumes= {"application/json", "application/xml"})
	    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "categoryId") @Min(1) Long categoryId,
	        @PathVariable(value = "productId")  @Min(1) Long productId, @Valid @RequestBody ProductRequest productRequest) throws ResourceNotFoundException {
	    	logger.info("categoryId --> "+categoryId + "productId --> "+productId +"productRequest -->"+productRequest.toString());
	    	ProductResponse productResponse = productBusinessImpl.updateProduct(categoryId, productId, productRequest);
	    	logger.info("productResponse"+productResponse.toString());
	    	return new ResponseEntity<>(productResponse, HttpStatus.OK);
	        
	    }

	    @DeleteMapping(value="/category/{categoryId}/product/{productId}" , produces= {"application/json", "application/xml"})
	    public ResponseEntity <Success> deleteProduct(@PathVariable(value = "categoryId") @Min(1) Long categoryId,
	        @PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
	    	logger.info("categoryId --> "+categoryId + "productId --> "+productId );
	    	productBusinessImpl.deleteProduct(categoryId, productId);
	    	logger.info("Deleted successfully");
	    	return new ResponseEntity<>(new Success("Deleted successfully"), HttpStatus.OK);	        
	    }
	}
