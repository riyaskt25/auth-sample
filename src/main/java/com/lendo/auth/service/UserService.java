package com.lendo.auth.service;

import java.util.List;

import com.lendo.auth.ext.rest.pojo.Comments;
import com.lendo.auth.ext.rest.pojo.Posts;
import com.lendo.auth.ext.rest.pojo.RestUser;

public interface UserService {

	RestUser[] getUsers(String token);

	Posts[] getPosts(String token);

	Comments[] getComments(String token);
	
	List<Posts> getUserPosts(String userId);
}
