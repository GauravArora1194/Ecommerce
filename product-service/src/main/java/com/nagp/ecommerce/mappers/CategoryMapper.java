package com.nagp.ecommerce.mappers;

import java.util.Set;

import com.nagp.ecommerce.dto.CategoryDTO;
import com.nagp.ecommerce.entity.Category;

public interface CategoryMapper {

	CategoryDTO map(Category category);

	Set<CategoryDTO> mapToDTO(Set<Category> category);

	Category map(CategoryDTO categoryDTO);

	Set<Category> map(Set<CategoryDTO> categoryDTO);

}
