package com.tejaswi.blog.controller;

import java.util.List;

import org.hibernate.grammars.hql.HqlParser.SortDirectionContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.tejaswi.blog.config.ConstantVariables;
import com.tejaswi.blog.entity.PostDto;
import com.tejaswi.blog.payload.AllPostResponse;
import com.tejaswi.blog.payload.ApiResponse;
import com.tejaswi.blog.service.postService;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private postService postService;

// create post
	@PostMapping("/user/{userId}/category/{catId}/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer catId) {
		PostDto createPostDto = this.postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);

	}

//delete post by post id
	@DeleteMapping("/post/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
		this.postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully!!!", true), HttpStatus.OK);
	}

//get post by category id
	@GetMapping("/post/category/{catId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId) {
		List<PostDto> allPostByCatDtos = this.postService.getPostByCategory(catId);
		return ResponseEntity.ok(allPostByCatDtos);
	}

/// get post by user id
	@GetMapping("/post/user/{uid}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer uid) {
		List<PostDto> allPostByuserDtos = this.postService.getPostByUser(uid);
		// return ResponseEntity.ok(allPostByuserDtos);
		return new ResponseEntity<List<PostDto>>(allPostByuserDtos, HttpStatus.OK);
	}

	// get post by post id

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable("postId") Integer uid) {
		PostDto postDto = this.postService.getPostById(uid);
		// return ResponseEntity.ok(allPostByuserDtos);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

// request param take input from url  http://localhost:9090/api/allPost?pageNumber=1&pageSize=2
//get all post  default value of page start with 0
	@GetMapping("/allPost")
	public ResponseEntity<AllPostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = ConstantVariables.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ConstantVariables.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = ConstantVariables.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = ConstantVariables.SORT_DIRECTION, required = false) String sortDirection) {
		AllPostResponse allPostDtos = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
		// return ResponseEntity.ok(allPostByuserDtos);
		return new ResponseEntity<AllPostResponse>(allPostDtos, HttpStatus.OK);
	}

	// update post

	@PutMapping("/post/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		// return ResponseEntity.ok(allPostByuserDtos);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	// search title

	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		System.out.println(keyword);
		List<PostDto> searchTitle = this.postService.searchPost(keyword);
		// return ResponseEntity.ok(allPostByuserDtos);
		return new ResponseEntity<List<PostDto>>(searchTitle, HttpStatus.OK);
	}
}
