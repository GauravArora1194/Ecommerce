package com.nagp.ecommerce.service;

import java.util.List;

import com.nagp.ecommerce.entity.Category;
import com.nagp.ecommerce.entity.Product;

public interface CategoryService {

	List<Product> getProductsForCategory(String category);
	
	void addCategory(Category category);
}
