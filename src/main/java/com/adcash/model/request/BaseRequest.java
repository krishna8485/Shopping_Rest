package com.adcash.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class BaseRequest {
	@NotEmpty(message = "Please provide a name")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message= "Provide valid name. Alphanumeric with space values are allowed.")
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
