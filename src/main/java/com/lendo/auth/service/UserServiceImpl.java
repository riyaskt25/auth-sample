package com.lendo.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lendo.auth.exception.AuthenticationException;
import com.lendo.auth.ext.rest.pojo.Comments;
import com.lendo.auth.ext.rest.pojo.Posts;
import com.lendo.auth.ext.rest.pojo.RestUser;
import com.lendo.auth.pojo.StringConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AuthenticationService authService;

	public RestUser[] getUsers(String token) {

		if (!authService.hasRole(token, StringConstants.VIEW_USERS)) {
			throw new AuthenticationException("Invalid credentials");
		}

		RestUser[] users = restTemplate.getForObject("https://gorest.co.in/public/v2/users", RestUser[].class);
		return users;
	}

	public Posts[] getPosts(String token) {

		if (!authService.hasRole(token, StringConstants.VIEW_POSTs)) {
			throw new AuthenticationException("Invalid credentials");
		}

		Posts[] posts = restTemplate.getForObject("https://gorest.co.in/public/v2/posts", Posts[].class);
		return posts;
	}

	public Comments[] getComments(String token) {

		if (!authService.hasRole(token, StringConstants.VIEW_COMMENTS)) {
			throw new AuthenticationException("Invalid credentials");
		}

		Comments[] comments = restTemplate.getForObject("https://gorest.co.in/public/v2/comments", Comments[].class);
		return comments;
	}
}
