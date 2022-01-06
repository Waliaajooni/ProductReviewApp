package com.nagarro.registration.service;

import java.util.List;
import com.nagarro.registration.models.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(long id);
	
	public Product getProductByName(String name);
	
	public List<Product> getProductsFromName(String sname);
}
