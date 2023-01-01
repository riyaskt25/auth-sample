package com.lendo.auth.service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lendo.auth.controller.request.UserDetails;
import com.lendo.auth.controller.response.AuthResult;
import com.lendo.auth.exception.AuthenticationException;
import com.lendo.auth.pojo.UserMetatada;
import com.lendo.auth.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServiceImpl.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AuthResult authenticate(UserDetails userDetails) {

		if (userDetails.getUserName() == null || userDetails.getUserPassword() == null
				|| userDetails.getUserName().isBlank() || userDetails.getUserName().isBlank()) {
			throw new IllegalArgumentException("Invalid username or passwor.");
		}

		UserMetatada metadata = userRepository.findUser(userDetails.getUserName());
		if (metadata == null || !userDetails.getUserPassword().equalsIgnoreCase(metadata.getUserPassword())) {
			throw new AuthenticationException("Invalid credentials");
		}
		String jwtToken = createToken(metadata);

		return new AuthResult(jwtToken);
	}

	private String createToken(UserMetatada metadata) {

		LOGGER.info("jwtSecret = " + jwtSecret);

		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());

		Instant now = Instant.now();
		String jwtToken = Jwts.builder().claim("name", "test name").claim("email", metadata.getEmail())
				.claim("roles", metadata.getRoles()).setSubject("test subject").setId(UUID.randomUUID().toString())
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(15l, ChronoUnit.MINUTES)))
				.signWith(hmacKey).compact();

		LOGGER.info("jwtToken = " + jwtToken);

		return jwtToken;
	}

	public boolean hasRole(String jwtString, String roleName) {
		
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS256.getJcaName());

		Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtString);

		List<String> userRoles = (List<String>) jwt.getBody().get("roles");

		LOGGER.info("from set = {}", userRoles);

		return userRoles.contains(roleName);
	}

}
