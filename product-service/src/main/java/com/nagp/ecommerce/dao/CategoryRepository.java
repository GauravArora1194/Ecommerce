package com.nagp.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
