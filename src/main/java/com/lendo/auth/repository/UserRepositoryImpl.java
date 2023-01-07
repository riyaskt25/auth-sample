package com.lendo.auth.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lendo.auth.pojo.UserMetatada;

@Repository
public class UserRepositoryImpl implements UserRepository {

	Map<String, UserMetatada> users = new HashMap<String, UserMetatada>();

	public UserMetatada findUser(String userName) {
		UserMetatada userFromDB = null;
		if ("admin".equals(userName)) {
			userFromDB = UserMetatada.builder().userName("admin").userPassword("admin").build();
		}
		return userFromDB;
	}

}
