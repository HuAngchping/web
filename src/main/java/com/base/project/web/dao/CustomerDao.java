package com.base.project.web.dao;

import com.base.project.web.model.CustomerRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(CustomerRegister customer) {
		String sql = "INSERT INTO CUSTOMER (USERNAME, PASSWORD, EMAIL, CREATE_AT) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getUsername(), customer.getPassword(), customer.getEmail(), new Date());
	}
}
