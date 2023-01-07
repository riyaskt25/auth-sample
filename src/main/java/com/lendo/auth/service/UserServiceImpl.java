package com.lendo.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lendo.auth.exception.AuthenticationException;
import com.lendo.auth.ext.rest.pojo.Comments;
import com.lendo.auth.ext.rest.pojo.Posts;
import com.lendo.auth.ext.rest.pojo.RestUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	public RestUser[] getUsers() {
		return restTemplate.getForObject("https://gorest.co.in/public/v2/users", RestUser[].class);
	}

	public Posts[] getPosts() {
		return restTemplate.getForObject("https://gorest.co.in/public/v2/posts", Posts[].class);
	}

	public Comments[] getComments() {
		return restTemplate.getForObject("https://gorest.co.in/public/v2/comments", Comments[].class);
	}

	public List<Posts> getUserPosts(String userId) {
		Posts[] posts = restTemplate.getForObject("https://gorest.co.in/public/v2/posts", Posts[].class);
		List<Posts> userPosts = java.util.Arrays.stream(posts).filter(post -> post.getUser_id().equals(userId))
				.collect(Collectors.toList());
		return userPosts;
	}
}
