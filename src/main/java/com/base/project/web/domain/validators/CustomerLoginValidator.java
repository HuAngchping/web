package com.base.project.web.domain.validators;

import com.base.project.web.entities.Customer;
import com.base.project.web.domain.CustomerLogin;
import com.base.project.web.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class CustomerLoginValidator implements Validator {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean supports(Class<?> clazz) {
        return CustomerLogin.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        CustomerLogin customerLogin = (CustomerLogin) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.null.error");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.null.error");
        if ((customerLogin.getUsername().length() > 6 || customerLogin.getUsername().length() < 3) && !StringUtils.isEmpty(customerLogin.getUsername())) {
            errors.rejectValue("username", "username.length.error");
        }

        Customer customer = customerRepository.findByUsername(customerLogin.getUsername());
        if (customer == null) {
            errors.rejectValue("username", "username.error");
        } else {
            if (!customer.getPassword().equals(customerLogin.getPassword())) {
                errors.rejectValue("username", "username.error");
            }
        }
    }

}
