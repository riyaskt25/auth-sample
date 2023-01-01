package com.lendo.auth.pojo;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMetatada {

	private String userName;
	private String userPassword;
	private String email;
	private Set<String> roles;

}
