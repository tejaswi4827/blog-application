package com.tejaswi.blog.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	private int id;
	@NotEmpty(message = "Name can't be empty!!!")
	private String name;
	@Email(message = "Email is not valid!!!")
	@NotEmpty(message = "Email can't be Empty!!!")
	private String email;

	@NotEmpty(message = "Password field can't be Empty!!!")
	@Size(min = 6,max = 14,message = "minimum length of password should be 6 char maximum 14 char!!!")
	private String password;
	@NotEmpty(message = "About filed can't be Empty!!!")
	private String about;
}
