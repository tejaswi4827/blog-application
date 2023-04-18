package com.tejaswi.blog.service;

import java.util.List;


import com.tejaswi.blog.entity.CategoryDto;


public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer categoryId);
}
