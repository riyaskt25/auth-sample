package com.lendo.auth.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.lendo.auth.pojo.StringConstants;
import com.lendo.auth.pojo.UserMetatada;

@Repository
public class UserRepositoryImpl implements UserRepository {

	Map<String, UserMetatada> users = new HashMap<String, UserMetatada>();

	public UserMetatada findUser(String userName) {
		if (users.isEmpty()) {
			popluateUses();
		}
		return users.get(userName);
	}

	private void popluateUses() {

		Set<String> adminRoles = new HashSet<String>();
		adminRoles.add(StringConstants.VIEW_USERS);
		adminRoles.add(StringConstants.VIEW_POSTs);
		adminRoles.add(StringConstants.VIEW_COMMENTS);
		UserMetatada admin = UserMetatada.builder().userName("admin").userPassword("admin@123")
				.email("admin@example.com").roles(adminRoles).build();
		users.put("admin", admin);

		Set<String> postRoleOnly = new HashSet<String>();
		postRoleOnly.add(StringConstants.VIEW_POSTs);
		UserMetatada post = UserMetatada.builder().userName("post").userPassword("post@123").email("post@example.com")
				.roles(postRoleOnly).build();
		users.put("post", post);

		Set<String> noaccess = new HashSet<String>();
		UserMetatada na = UserMetatada.builder().userName("noaccess").userPassword("noaccess@123")
				.email("noaccess@example.com").roles(noaccess).build();
		users.put("noaccess", na);

	}

}
