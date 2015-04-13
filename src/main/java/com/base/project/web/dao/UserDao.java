package com.base.project.web.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.base.project.web.model.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(User user) {
		String sql = "INSERT INTO USER (USERNAME, PASSWORD,CREATE_AT) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), new Date());
	}
}
