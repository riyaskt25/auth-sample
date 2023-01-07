package com.lendo.auth.service;

import java.util.List;

import com.lendo.auth.ext.rest.pojo.Comments;
import com.lendo.auth.ext.rest.pojo.Posts;
import com.lendo.auth.ext.rest.pojo.RestUser;

public interface UserService {

	RestUser[] getUsers();

	Posts[] getPosts();

	Comments[] getComments();

	List<Posts> getUserPosts(String userId);
}
