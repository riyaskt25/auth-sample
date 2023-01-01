package com.lendo.auth.service;

import com.lendo.auth.ext.rest.pojo.RestUser;

public interface UserService {

	RestUser[] getUsers(String token);
}
