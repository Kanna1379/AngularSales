package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	public Product findById(Integer id);
	public void saveOrUpdate(Product product);
	public void deleteById(int productId);
}
