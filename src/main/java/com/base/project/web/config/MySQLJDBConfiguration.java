package com.base.project.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class MySQLJDBConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
	@Description("mysql data source init")
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		data.setUrl(env.getProperty("jdbc.url"));
		data.setUsername(env.getProperty("jdbc.username"));
		data.setPassword(env.getProperty("jdbc.password"));
		return data;
	}
	
	@Bean
	@Description("jdbc init")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate(dataSource());
		return template;
	}
}
