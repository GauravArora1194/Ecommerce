package com.nagp.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.ecommerce.dto.CategoryDTO;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.mappers.CategoryMapper;
import com.nagp.ecommerce.mappers.ProductMapper;
import com.nagp.ecommerce.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping(value = "/products/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	

	@GetMapping("/{code}")
	public List<ProductDTO> getProductsForCategory(@PathVariable String code) {

		return productMapper.mapToDTO(categoryService.getProductsForCategory(code));

	}
	
	@PostMapping
	public void addCategory(@RequestBody CategoryDTO category) {
		
		categoryService.addCategory(categoryMapper.map(category));
	}

}
