package com.base.project.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by huang on 4/17/15.
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.base.project.web.repositories")
public class MySQLHibernateConfiguration {

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
    @Description("jpa entity config")
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.base.project.web.entities");

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(false);
        jpaVendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));

        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
