package com.tejaswi.blog.service;

import java.util.List;

import com.tejaswi.blog.entity.PostDto;
import com.tejaswi.blog.payload.AllPostResponse;

public interface postService {
	PostDto createPost(PostDto postDto,Integer userId,Integer catId);

	PostDto updatePost(PostDto postDto,Integer postId);

	void deletePost(Integer postId);

	AllPostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy, String sortDirection);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

	List<PostDto> searchPost(String keyword);
}
