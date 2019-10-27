package com.adcash.controller;

import com.adcash.business.IBusiness;
import com.adcash.business.Impl.CategoryBusinessImpl;
import com.adcash.exception.ResourceNotFoundException;
import com.adcash.model.request.CategoryRequest;
import com.adcash.model.response.CategoryResponse;
import com.adcash.model.response.Success;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class CategoryController {
	
	private static final Logger logger = LogManager.getLogger(CategoryBusinessImpl.class);

    @Autowired @Qualifier("categoryBusinessImpl")
    private IBusiness categoryBusinessImpl;
   
    @GetMapping(value="/categories", produces= {"application/json", "application/xml"})
    ResponseEntity<List<CategoryResponse>> getAllCategory() throws ResourceNotFoundException{
    	List<CategoryResponse> categoryResponses = (List<CategoryResponse>)categoryBusinessImpl.findAll();
    	logger.info(categoryResponses.toString());
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    
    @PostMapping(value="/category", produces= {"application/json", "application/xml"}, consumes= {"application/json", "application/xml"})
    ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
    	logger.info(categoryRequest.toString());
        CategoryResponse categoryResponse =  categoryBusinessImpl.save(categoryRequest);
    	logger.info(categoryResponse.toString());
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping(value="/category/{categoryId}", produces= {"application/json", "application/xml"})
    ResponseEntity<CategoryResponse> findCategory(@PathVariable @Min(1) Long categoryId) throws ResourceNotFoundException {
    	logger.info("categoryId -->"+ categoryId.toString());
        CategoryResponse categoryResponse =  categoryBusinessImpl.findById(categoryId);
        logger.info(categoryResponse);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping(value="/category/{categoryId}", produces= {"application/json", "application/xml"}, consumes= {"application/json", "application/xml"})
    ResponseEntity<CategoryResponse> updateCategory( @Valid @RequestBody CategoryRequest categoryRequest, @PathVariable @Min(1) Long categoryId) {
    	logger.info("categoryRequest --> "+ categoryRequest.toString() +"categoryId -->"+categoryId.toString());
        CategoryResponse categoryResponse =  categoryBusinessImpl.updateById(categoryRequest, categoryId);
        logger.info("categoryResponse --> "+ categoryResponse.toString() );
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping(value="/category/{categoryId}",produces= {"application/json", "application/xml"})
    ResponseEntity<Success> deleteCategory(@PathVariable @Min(1) Long categoryId) throws ResourceNotFoundException{
    	logger.info("categoryId -->"+categoryId.toString());
    	categoryBusinessImpl.deleteById(categoryId);
    	return new ResponseEntity<>(new Success("Deleted Successfully"), HttpStatus.OK);
    }

}
