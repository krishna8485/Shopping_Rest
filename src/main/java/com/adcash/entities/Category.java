package com.adcash.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category {
	
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "cate_id")
	    private Long id;
	 
	 	@NotEmpty(message = "Please provide a Category name")
	    private String cname;
	 	
	 	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	    private Set<Product> product;
	 	
	 	public Category() {
	 		super();
	 	}
	
		

		public Category(@NotEmpty(message = "Please provide a Category name") String cname) {
			super();
			this.cname = cname;
		}
		

		public Category(Long id, @NotEmpty(message = "Please provide a Category name") String cname) {
			super();
			this.id = id;
			this.cname = cname;
		}



		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @return the cname
		 */
		public String getCname() {
			return cname;
		}

		/**
		 * @return the items
		 */
		public Set<Product> getProduct() {
			return product;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @param cname the cname to set
		 */
		public void setCname(String cname) {
			this.cname = cname;
		}

		/**
		 * @param items the items to set
		 */
		public void setProduct(Set<Product> product) {
			this.product = product;
		}

}
