package com.tejaswi.blog.entity;

import java.sql.Date;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class PostDto {
	private int postId;
	@NotBlank(message = "title can't be Empty!!!")
	private String title;
	@Size(max = 10000, message = "max size is 10000")
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto categoryDetails;
	private UserDto userDetails;
}
