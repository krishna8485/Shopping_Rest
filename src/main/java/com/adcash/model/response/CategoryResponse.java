package com.adcash.model.response;

public class CategoryResponse  extends BaseResponse{

	public CategoryResponse() {
		super();
	}
	
	public CategoryResponse(Long id, String name) {
		super(id, name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryResponse [getId()=" + getId() + ", getName()=" + getName() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	

}
