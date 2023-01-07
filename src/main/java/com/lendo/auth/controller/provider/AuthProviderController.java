package com.lendo.auth.controller.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendo.auth.controller.request.UserDetails;
import com.lendo.auth.controller.response.AuthResult;
import com.lendo.auth.controller.response.ResponseWrapper;
import com.lendo.auth.service.AuthenticationService;

@RestController
public class AuthProviderController {

	private static final Logger LOGGER = LogManager.getLogger(AuthProviderController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(path = "/authenticate")
	public ResponseWrapper register(UserDetails userDetails) throws Exception {

		LOGGER.info("Executing authenticate API..");

		AuthResult res = authenticationService.authenticate(userDetails);

		return ResponseWrapper.builder().result(res).build();

	}

}
