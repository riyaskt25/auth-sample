package com.lendo.auth.controller.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.auth.controller.response.ResponseWrapper;
import com.lendo.auth.ext.rest.pojo.Comments;
import com.lendo.auth.ext.rest.pojo.Posts;
import com.lendo.auth.ext.rest.pojo.RestUser;
import com.lendo.auth.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UsersController {

	private static final Logger LOGGER = LogManager.getLogger(UsersController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseWrapper getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = request.getHeader("Authorization");
		LOGGER.info("Authorization header value= {}", token);
		RestUser[] users = userService.getUsers(token);
		return ResponseWrapper.builder().result(users).build();
	}

	@GetMapping("/posts")
	public ResponseWrapper getPosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = request.getHeader("Authorization");
		LOGGER.info("Authorization header value= {}", token);
		Posts[] posts = userService.getPosts(token);
		return ResponseWrapper.builder().result(posts).build();
	}

	@GetMapping("/comments")
	public ResponseWrapper getComments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = request.getHeader("Authorization");
		LOGGER.info("Authorization header value= {}", token);
		Comments[] comments = userService.getComments(token);
		return ResponseWrapper.builder().result(comments).build();
	}

	@GetMapping("/posts/{userId}")
	public ResponseWrapper getPosts(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String userId) throws Exception {
		String token = request.getHeader("Authorization");
		LOGGER.info("Authorization header value= {}", token);
		List<Posts> posts= userService.getUserPosts(userId);
		return ResponseWrapper.builder().result(posts).build();
	}

}
