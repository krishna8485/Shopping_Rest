package com.adcash.business;

import java.util.List;

import com.adcash.exception.ResourceNotFoundException;
import com.adcash.model.request.BaseRequest;
import com.adcash.model.response.BaseResponse;

//TODO: Need to restructure this class to make implement in productbusinessimpl class
public interface IBusiness {
	List<? extends BaseResponse> findAll() throws ResourceNotFoundException;
	<T extends BaseResponse> T findById(Long id) throws ResourceNotFoundException;
	<T extends BaseResponse> T updateById(BaseRequest baseRequest, Long id);
	void deleteById(Long id) throws ResourceNotFoundException;
	<T extends BaseResponse> T save(BaseRequest baseRequest);
	
}
