package com.tejaswi.blog.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejaswi.blog.entity.Category;
import com.tejaswi.blog.entity.CategoryDto;
import com.tejaswi.blog.exception.ResourceNotFoundException;
import com.tejaswi.blog.repo.CategoryRepo;
import com.tejaswi.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		// System.out.println("inside se"+categoryDto.getCategoryTitle()+" "+
		// categoryDto.getCategoryDesc());
		Category createdCategory = categoryRepo.save(dtoTocategory(categoryDto));
		return this.categoryToDto(createdCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category categoryById = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		categoryById.setCategoryTitle(categoryDto.getCategoryTitle());
		categoryById.setCategoryDesc(categoryDto.getCategoryDesc());
		Category updCategory = this.categoryRepo.save(categoryById);
		return categoryToDto(updCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category categorybyId = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		CategoryDto categoryDto = categoryToDto(categorybyId);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = this.categoryRepo.findAll();
		ArrayList<CategoryDto> categoryDtos = new ArrayList<>();
		for(Category category : categories)
		{
			// Converting category to dto then adding to list
			categoryDtos.add(categoryToDto(category));
		}
		return categoryDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		this.categoryRepo.deleteById(categoryId);

	}

	public CategoryDto categoryToDto(Category category) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	public Category dtoTocategory(CategoryDto categoryDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
}
