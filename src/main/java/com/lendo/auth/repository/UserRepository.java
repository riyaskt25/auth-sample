package com.lendo.auth.repository;

import com.lendo.auth.pojo.UserMetatada;

public interface UserRepository {

	UserMetatada findUser(String userName);
}
