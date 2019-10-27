package com.adcash.model.request;

import javax.validation.constraints.NotEmpty;

public class BaseRequest {
	@NotEmpty(message = "Please provide a name")
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "BaseRequest [name=" + name + "]";
	}
	
}
