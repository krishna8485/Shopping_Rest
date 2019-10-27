package com.adcash.model.response;

public class BaseResponse {
	private Long id;
	private String name;
	public BaseResponse(Long id, String name) {
		this.id = id;
		this.name=name;
	}
	public BaseResponse() {
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BaseResponse [id=" + id + ", name=" + name +  "]";
	}
	
	
	

}
