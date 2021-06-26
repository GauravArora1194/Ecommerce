package com.nagp.ecommerce.service;

import java.util.List;

import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.entity.Product;

public interface ProductService {

	List<Product> fetchAllProducts();

	Product fetchProduct(Long id);
	
	ProductDTO fetchProduct(String code);

	Product addProduct(Product product);

	Product updateProduct(Long id, ProductDTO product);

	void deleteProduct(Long id);

}
