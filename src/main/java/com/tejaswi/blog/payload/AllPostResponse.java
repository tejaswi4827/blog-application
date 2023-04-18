package com.tejaswi.blog.payload;

import java.util.List;

import com.tejaswi.blog.entity.PostDto;

import lombok.Data;

@Data
public class AllPostResponse {
	private List<PostDto> postContent;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private long totalElement;
	private boolean lastPage;
}
