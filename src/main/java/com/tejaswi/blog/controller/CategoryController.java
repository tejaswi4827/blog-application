package com.tejaswi.blog.controller;

import java.nio.channels.NonReadableChannelException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejaswi.blog.entity.Category;
import com.tejaswi.blog.entity.CategoryDto;
import com.tejaswi.blog.payload.ApiResponse;
import com.tejaswi.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		// System.out.println(categoryDto.getCategoryTitle()+" "+
		// categoryDto.getCategoryDesc());
		CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCategory/{catId}")
	public ResponseEntity<ApiResponse> createCategory(@PathVariable Integer catId) {

		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("/updateCategory/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		CategoryDto upCategoryDto = this.categoryService.updateCategory(categoryDto, catId);
		return ResponseEntity.ok(upCategoryDto);

	}
	@GetMapping("/categoryById/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId) {
	CategoryDto category = 	this.categoryService.getCategoryById(catId);
		return ResponseEntity.ok(category);
	}
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
	List<CategoryDto> allCategoryDtos =	this.categoryService.getAllCategory();
		return  ResponseEntity.ok(allCategoryDtos);
		
	}
}
