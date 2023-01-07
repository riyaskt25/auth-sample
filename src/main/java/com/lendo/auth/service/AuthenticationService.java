package com.lendo.auth.service;

import com.lendo.auth.controller.request.UserDetails;
import com.lendo.auth.controller.response.AuthResult;

public interface AuthenticationService {

	AuthResult authenticate(UserDetails userDetails);

}
