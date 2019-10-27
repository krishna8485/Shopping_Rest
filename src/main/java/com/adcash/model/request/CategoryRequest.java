package com.adcash.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CategoryRequest")
public class CategoryRequest extends BaseRequest {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryRequest [getName()=" + getName() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
