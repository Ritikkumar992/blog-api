package com.blogapp.services;

import java.util.List;

import com.blogapp.paylods.PostDto;
import com.blogapp.paylods.PostResponse;

public interface PostService {

	// Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);

	// get ALL posts

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


	// get Single Post
	PostDto getPostById(Integer postId);


	// get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// get all posts by User
	List<PostDto> getPostsByUser(Integer userId);

	// search post
	List<PostDto> searchPosts(String keyword);


}
