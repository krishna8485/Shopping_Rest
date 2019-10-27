package com.adcash.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProductRequest")
public class ProductRequest extends BaseRequest{

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductRequest [getName()=" + getName() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
