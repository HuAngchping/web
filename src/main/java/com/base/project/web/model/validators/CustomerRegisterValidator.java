package com.base.project.web.model.validators;

import com.base.project.web.model.CustomerRegister;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class CustomerRegisterValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return CustomerRegister.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		CustomerRegister customerRegister = (CustomerRegister) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.null.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.null.error");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.null.error");
		if ((customerRegister.getUsername().length() > 6 || customerRegister.getUsername().length() < 3) && !StringUtils.isEmpty(customerRegister.getUsername())) {
	        errors.rejectValue("username", "username.length.error");
        }
	}

}
