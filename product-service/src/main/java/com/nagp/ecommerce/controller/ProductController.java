package com.nagp.ecommerce.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagp.ecommerce.dto.ProductUpdatesDto;
import com.nagp.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.mappers.ProductMapper;
import com.nagp.ecommerce.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper mapper;

	@GetMapping
	public List<ProductDTO> fetchAllProducts() {
		return mapper.mapToDTO(productService.fetchAllProducts());
	}

	@GetMapping("/{code}")
	public ProductDTO fetchProduct(@PathVariable String code) {
		
		System.out.println("Fetching product from DB");
		return productService.fetchProduct(code);
		// return mapper.map(productService.fetchProduct(code));
	}

	@PostMapping
	public ProductDTO addProduct(@RequestBody ProductDTO product) {
		Product map = new ObjectMapper().convertValue(product, Product.class); //mapper.map(product);
		return mapper.map(productService.addProduct(map));
	}

	@DeleteMapping("/{productId}")
	public void removeProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
	}

	@PutMapping("/{productId}")
	public ProductDTO updateProduct(@RequestBody ProductDTO product, @PathVariable Long productId) {
		return mapper.map(productService.updateProduct(productId, product));
	}

}
