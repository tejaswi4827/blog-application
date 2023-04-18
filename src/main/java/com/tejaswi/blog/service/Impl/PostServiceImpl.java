package com.tejaswi.blog.service.Impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.grammars.hql.HqlParser.SortDirectionContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tejaswi.blog.entity.Category;
import com.tejaswi.blog.entity.Post;
import com.tejaswi.blog.entity.PostDto;
import com.tejaswi.blog.entity.User;
import com.tejaswi.blog.entity.UserDto;
import com.tejaswi.blog.exception.ResourceNotFoundException;
import com.tejaswi.blog.payload.AllPostResponse;
import com.tejaswi.blog.repo.CategoryRepo;
import com.tejaswi.blog.repo.UserRepo;
import com.tejaswi.blog.repo.postRepo;
import com.tejaswi.blog.service.postService;

@Service
public class PostServiceImpl implements postService {
	@Autowired
	private postRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		User userById = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category categoryById = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		post.setUser(userById);
		post.setCategory(categoryById);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		PostDto postDtos = this.modelMapper.map(updatedPost, PostDto.class);

		return postDtos;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post postIds = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		this.postRepo.delete(postIds);
	}

	@Override
	public AllPostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		// added pagination concept as jpa repo implement pagination and sorting
		// internally
		
		// sorting dynamically from postman getallpost api
		Sort sort = null;
		if (sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> postByPage = this.postRepo.findAll(pageable);
		List<Post> posts = postByPage.getContent();
		List<PostDto> postDtos = new ArrayList<>();
		for (Post post : posts) {
			postDtos.add(this.modelMapper.map(post, PostDto.class));
		}
		// changing post request by adding extra class AllPostResponse
		AllPostResponse allPostResponse = new AllPostResponse();
		allPostResponse.setPostContent(postDtos);
		allPostResponse.setPageNumber(postByPage.getNumber());
		allPostResponse.setPageSize(postByPage.getSize());
		allPostResponse.setTotalElement(postByPage.getTotalElements());
		allPostResponse.setTotalPage(postByPage.getTotalPages());
		allPostResponse.setLastPage(postByPage.isLast());
		return allPostResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post postIds = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		PostDto postDto = this.modelMapper.map(postIds, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Category categoryIds = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> allPosts = this.postRepo.findByCategory(categoryIds);

		/// converting post to post dto
		List<PostDto> postDtos = new ArrayList<>();
		for (Post user : allPosts) {
			postDtos.add(this.modelMapper.map(user, PostDto.class));
		}
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User userIds = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Post> allUser = this.postRepo.findByUser(userIds);
		List<PostDto> postForUserDtos = new ArrayList<>();

		for (Post user : allUser) {
			postForUserDtos.add(this.modelMapper.map(user, PostDto.class));
		}
		return postForUserDtos;

	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<Post> allList = this.postRepo.searchTitle("%" + keyword + "%");
		List<PostDto> allTitle = new ArrayList<>();
		for (Post post : allList) {
			allTitle.add(this.modelMapper.map(post, PostDto.class));
		}
		return allTitle;
	}

}
