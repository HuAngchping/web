package com.base.project.web.service;

import com.base.project.web.entities.Customer;
import com.base.project.web.domain.CustomerRegister;
import com.base.project.web.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by huang on 4/23/15.
 */
@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void createAccount(CustomerRegister register) {
        Customer customer = new Customer();
        customer.setUsername(register.getUsername());
        customer.setPassword(register.getPassword());
        customer.setEmail(register.getEmail());
        customer.setCreate_at(new Date());
        customerRepository.save(customer);
    }
}
