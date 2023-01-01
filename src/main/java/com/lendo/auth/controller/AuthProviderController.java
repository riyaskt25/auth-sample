package com.lendo.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.auth.controller.response.ResponseWrapper;
import com.lendo.auth.controller.request.UserDetails;
import com.lendo.auth.controller.response.AuthResult;
import com.lendo.auth.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthProviderController {

	private static final Logger LOGGER = LogManager.getLogger(AuthProviderController.class);

	private AuthenticationService authenticationService;

	public AuthProviderController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;

	}

	@PostMapping(path = "/authenticate")
	public ResponseWrapper register(HttpServletRequest request, HttpServletResponse response, UserDetails userDetails)
			throws Exception {

		AuthResult res = authenticationService.authenticate(userDetails);

		return ResponseWrapper.builder().result(res).build();

	}

}
