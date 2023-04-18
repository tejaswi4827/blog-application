package com.tejaswi.blog.entity;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class CategoryDto {

	private int categoryId;
	@NotEmpty(message = "Title can't be empty!!!")
	private String categoryTitle;
	@NotEmpty(message = "Description can't be empty!!!")
	@Size(min = 0,max=200)
	private String categoryDesc;

}
