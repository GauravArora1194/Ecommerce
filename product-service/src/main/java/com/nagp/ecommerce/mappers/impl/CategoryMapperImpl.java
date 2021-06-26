package com.nagp.ecommerce.mappers.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nagp.ecommerce.dto.CategoryDTO;
import com.nagp.ecommerce.entity.Category;
import com.nagp.ecommerce.mappers.CategoryMapper;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO map(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCode( category.getCode() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setSuperCategories( mapToDTO( category.getSuperCategories() ) );

        return categoryDTO;
    }

    @Override
    public Set<CategoryDTO> mapToDTO(Set<Category> category) {
        if ( category == null ) {
            return null;
        }

        Set<CategoryDTO> set = new HashSet<CategoryDTO>( Math.max( (int) ( category.size() / .75f ) + 1, 16 ) );
        for ( Category category1 : category ) {
            set.add( map( category1 ) );
        }

        return set;
    }

    @Override
    public Category map(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setCode( categoryDTO.getCode() );
        category.setDescription( categoryDTO.getDescription() );
        category.setName( categoryDTO.getName() );
        category.setSuperCategories( map( categoryDTO.getSuperCategories() ) );

        return category;
    }

    @Override
    public Set<Category> map(Set<CategoryDTO> categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Set<Category> set = new HashSet<Category>( Math.max( (int) ( categoryDTO.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO1 : categoryDTO ) {
            set.add( map( categoryDTO1 ) );
        }

        return set;
    }
}
