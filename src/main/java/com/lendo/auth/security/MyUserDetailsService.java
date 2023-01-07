package com.lendo.auth.security;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lendo.auth.pojo.UserMetatada;
import com.lendo.auth.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserMetatada um = userRepository.findUser(username);

		return new User(username, um.getUserPassword(), new ArrayList<>());
	}

}
