package com.nagarro.registration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.registration.dao.ProductDao;
import com.nagarro.registration.models.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		List<Product> prodList = productDao.findAll();
		return prodList;
	}
	
	@Override
	public List<Product> getProductsFromName(String sname) {
		List<Product> prodList = getAllProducts();
		List<Product> searchedProdList = new ArrayList<>();
		
		for (Product prod: prodList) {
			if (prod.getProdName().contains(sname)) {
				searchedProdList.add(prod);
			}
		}
		return searchedProdList;
	}


	@Override
	public Product getProductById(long id) {
		Product prod = productDao.findById(id).get();
		return prod;
	}

	@Override
	public Product getProductByName(String name) {
		List<Product> prodList = getAllProducts();
		
		for (Product product: prodList) {
			if (product.getProdName().equals(name)) {
				return product;
			}
		}
		
		return null;
	}

}
