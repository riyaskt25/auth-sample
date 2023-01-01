package com.lendo.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lendo.auth.exception.AuthenticationException;
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
}
