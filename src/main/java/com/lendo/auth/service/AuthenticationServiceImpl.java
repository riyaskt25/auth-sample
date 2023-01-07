package com.lendo.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lendo.auth.controller.request.UserDetails;
import com.lendo.auth.controller.response.AuthResult;
import com.lendo.auth.exception.AuthenticationException;
import com.lendo.auth.pojo.UserMetatada;
import com.lendo.auth.repository.UserRepository;
import com.lendo.auth.security.JwtServcie;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtServcie jwtServcie;

	@Override
	public AuthResult authenticate(UserDetails userDetails) {

		LOGGER.info("username={}", userDetails.getUserName());
		LOGGER.info("Password={}", userDetails.getUserPassword());

		if (userDetails.getUserName() == null || userDetails.getUserPassword() == null
				|| userDetails.getUserName().isBlank() || userDetails.getUserName().isBlank()) {
			throw new IllegalArgumentException("Invalid username or passwor.");
		}

		UserMetatada metadata = userRepository.findUser(userDetails.getUserName());
		if (metadata == null || !userDetails.getUserPassword().equalsIgnoreCase(metadata.getUserPassword())) {
			throw new AuthenticationException("Invalid credentials");
		}

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDetails.getUserName(), userDetails.getUserPassword()));

		String jwtToken = jwtServcie.generateToken(userDetails.getUserName());

		return new AuthResult(jwtToken);
	}

}
