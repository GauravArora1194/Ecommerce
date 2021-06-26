package com.nagp.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ecommerce.dao.CategoryRepository;
import com.nagp.ecommerce.entity.Category;
import com.nagp.ecommerce.entity.Product;
import com.nagp.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Product> getProductsForCategory(String categoryCode) {

		Optional<Category> category = categoryRepository.findById(categoryCode);

		if (category.isPresent()) {

			return new ArrayList<>(category.get().getProducts());
		}

		return null;
	}

	@Override
	public void addCategory(Category category) {

		categoryRepository.save(category);

	}

}
