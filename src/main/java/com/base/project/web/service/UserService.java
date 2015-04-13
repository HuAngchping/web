package com.base.project.web.service;

import org.springframework.stereotype.Component;

@Component
public class UserService {

	public void login(String username, String password, String email) {
		System.out.println(username + ", " + password + ", " + email + ".");
	}
}
