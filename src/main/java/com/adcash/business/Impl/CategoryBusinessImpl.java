package com.adcash.business.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adcash.business.IBusiness;
import com.adcash.entities.Category;
import com.adcash.exception.ResourceNotFoundException;
import com.adcash.model.request.BaseRequest;
import com.adcash.model.response.CategoryResponse;
import com.adcash.service.BaseService;

@Component("categoryBusinessImpl")
public class CategoryBusinessImpl implements IBusiness{
	
	@Autowired
	BaseService baseService;	

    private static final Logger logger = LogManager.getLogger(CategoryBusinessImpl.class);
	
    @Override
	public List<CategoryResponse> findAll() throws ResourceNotFoundException {
		
		List<CategoryResponse> categoryResponses = new ArrayList<>();		
		baseService.findAll()
						  .forEach(category->{
							  CategoryResponse categoryResponse = new CategoryResponse();
							  categoryResponse.setId(category.getId());
							  categoryResponse.setName(category.getCname());
							  categoryResponses.add(categoryResponse);
							  
						  });
		if(categoryResponses==null || categoryResponses.isEmpty()) {
			throw new ResourceNotFoundException("No Data Found");
		}
		return categoryResponses;
	}
	
    @Override
	public CategoryResponse findById(Long categoryId) throws ResourceNotFoundException{
		
		CategoryResponse categoryResponse = new CategoryResponse();
		
		baseService.findById(categoryId)
						  .map(category -> {
							  	categoryResponse.setName(category.getCname());
							  	categoryResponse.setId(category.getId());
							  	return categoryResponse;
						  })
						  .orElseThrow(() -> new ResourceNotFoundException("No Data found "+ categoryId));
		return categoryResponse;
	}
	
    @Override
	public CategoryResponse updateById(BaseRequest categoryRequest, Long categoryId){
			
			Category categoryOuter = baseService.findById(categoryId)
							  .map(category -> {
								  category.setCname(categoryRequest.getName());
								  	return baseService.save(category);
							  })
							  .orElseGet(() -> {
								  Category category = new Category(categoryRequest.getName());
								  return baseService.save(category);
							  });
			return  new CategoryResponse(categoryOuter.getId(), categoryOuter.getCname());
		}
	
	
    @Override
	public void deleteById(Long categoryId) throws ResourceNotFoundException {
    	baseService.findById(categoryId).map(category -> {
    		baseService.delete(category);
			 return category;
	        }).orElseThrow(() -> new ResourceNotFoundException(
	            "categoryId not found id " + categoryId ));	
	}
	
    @Override
	public CategoryResponse save(BaseRequest categoryRequest) {
		Category c = new Category(categoryRequest.getName());
		CategoryResponse categoryResponse = new CategoryResponse();
		
		Optional.of(baseService.save(c))
									.map(category -> {
										categoryResponse.setId(category.getId());
										categoryResponse.setName(category.getCname());
										return categoryResponse;
									});
		return categoryResponse;
		
	}

}
