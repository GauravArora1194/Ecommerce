package com.nagp.ecommerce.mappers.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nagp.ecommerce.dto.CategoryDTO;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.entity.Category;
import com.nagp.ecommerce.entity.Product;
import com.nagp.ecommerce.mappers.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {

	@Override
	public List<ProductDTO> mapToDTO(List<Product> product) {
		if (product == null) {
			return null;
		}

		List<ProductDTO> list = new ArrayList<ProductDTO>(product.size());
		for (Product product1 : product) {
			list.add(map(product1));
		}

		return list;
	}

	@Override
	public List<Product> map(List<ProductDTO> product) {
		if (product == null) {
			return null;
		}

		List<Product> list = new ArrayList<Product>(product.size());
		for (ProductDTO productDTO : product) {
			list.add(map(productDTO));
		}

		return list;
	}

	@Override
	public Product map(ProductDTO productDTO) {
		if (productDTO == null) {
			return null;
		}

		Product product = new Product();

		product.setBrand(productDTO.getBrand());
		product.setCategories(categoryDTOSetToCategorySet(productDTO.getCategories()));
		product.setCode(productDTO.getCode());
		product.setDescription(productDTO.getDescription());
		product.setDiscount(productDTO.getDiscount());
		product.setImageUrl(productDTO.getImageUrl());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		product.setTax(productDTO.getTax());

		return product;
	}

	@Override
	public ProductDTO map(Product product) {
		if (product == null) {
			return null;
		}

		ProductDTO productDTO = new ProductDTO();

		productDTO.setBrand(product.getBrand());
		productDTO.setCategories(categorySetToCategoryDTOSet(product.getCategories()));
		productDTO.setCode(product.getCode());
		productDTO.setDescription(product.getDescription());
		productDTO.setDiscount(product.getDiscount());
		productDTO.setImageUrl(product.getImageUrl());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setStock(product.getStock());
		productDTO.setTax(product.getTax());

		return productDTO;
	}

	protected Set<Category> categoryDTOSetToCategorySet(Set<CategoryDTO> set) {
		if (set == null) {
			return null;
		}

		Set<Category> set1 = new HashSet<Category>(Math.max((int) (set.size() / .75f) + 1, 16));
		for (CategoryDTO categoryDTO : set) {
			set1.add(categoryDTOToCategory(categoryDTO));
		}

		return set1;
	}

	protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
		if (categoryDTO == null) {
			return null;
		}

		Category category = new Category();

		category.setCode(categoryDTO.getCode());
		category.setDescription(categoryDTO.getDescription());
		category.setName(categoryDTO.getName());
		category.setSuperCategories(categoryDTOSetToCategorySet(categoryDTO.getSuperCategories()));

		return category;
	}

	protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
		if (set == null) {
			return null;
		}

		Set<CategoryDTO> set1 = new HashSet<CategoryDTO>(Math.max((int) (set.size() / .75f) + 1, 16));
		for (Category category : set) {
			set1.add(categoryToCategoryDTO(category));
		}

		return set1;
	}

	protected CategoryDTO categoryToCategoryDTO(Category category) {
		if (category == null) {
			return null;
		}

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setCode(category.getCode());
		categoryDTO.setDescription(category.getDescription());
		categoryDTO.setName(category.getName());
		categoryDTO.setSuperCategories(categorySetToCategoryDTOSet(category.getSuperCategories()));

		return categoryDTO;
	}
}
