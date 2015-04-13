package com.base.project.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.project.web.dao.UserDao;
import com.base.project.web.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public void login(String username, String password, String email) {
		System.out.println(username + ", " + password + ", " + email + ".");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		dao.insert(user);
	}
}
