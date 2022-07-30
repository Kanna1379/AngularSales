package com.example.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProductCategory")
public class ProductCategory {
 
	@Id
	@Column(name="categoryid")
	private int categoryid;
	
	@Column(name="categoryname")
	private String categoryname;

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public ProductCategory(int categoryid, String categoryname) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
	}
	
	public ProductCategory() {
		
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
	}
	
	
	
}
