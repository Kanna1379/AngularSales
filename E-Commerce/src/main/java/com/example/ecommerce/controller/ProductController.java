package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController
{
	@Autowired
	private ProductService productService;
	
	@GetMapping("/productList")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/prod/{productId}")
	public Product findById(@PathVariable Integer productId) throws Exception {
		Product p = productService.findById(productId);

		if (p == null)
			throw new Exception("Product ID : " + productId + " details not available in Repository");

		return p;
	}

	@PostMapping("/saveProduct")
	public String saveEmployee(@RequestBody Product product) {
		product.setProductid(0);
		productService.saveOrUpdate(product);

		return "Product details added successfully";
	}

	@PutMapping("/updateProduct")
	public String updateEmployee(@RequestBody Product product) {
	
		
		productService.saveOrUpdate(product);
		
		return "Product ID : "+ product.getProductid() + " Details updated Succesfully"; 
	}

	@DeleteMapping("prod/{id}")
	public String deleteById(@PathVariable int id) {
		productService.deleteById(id);
		return "Product ID : " + id +" details  deleted";
	}
}
	

