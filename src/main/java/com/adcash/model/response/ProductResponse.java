package com.adcash.model.response;

public class ProductResponse extends BaseResponse{

	public ProductResponse(long id, String pname) {
		super(id, pname);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductResponse [getId()=" + getId() + ", getName()=" + getName() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
