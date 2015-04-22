package com.base.project.web.service;

import com.base.project.web.model.CustomerRegister;
import com.base.project.web.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void login(String username, String password) {

    }

    public void signup(String username, String password, String email) {
        System.out.println(username + ", " + password + ", " + email + ".");
        CustomerRegister customer = new CustomerRegister();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customerRepository.save(customer);
    }
}
