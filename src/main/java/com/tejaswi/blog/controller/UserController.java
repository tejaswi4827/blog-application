package com.tejaswi.blog.controller;

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


import com.tejaswi.blog.entity.UserDto;
import com.tejaswi.blog.payload.ApiResponse;
import com.tejaswi.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

	// post request to create users
	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/updatedUser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer userId) {
		UserDto updateUserDto = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUserDto);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer userid) {
		UserDto getUsers = this.userService.getUserById(userid);
		return ResponseEntity.ok(getUsers);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer userid) {
	this.userService.deleteUser(userid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully!!!",true),HttpStatus.OK);
	}
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
	List<UserDto> allUserDto = this.userService.getAllUser();
	return ResponseEntity.ok(allUserDto);
	}
}
