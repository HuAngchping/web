package com.base.project.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.project.web.model.User;
import com.base.project.web.model.validators.UserLoginFormValidator;
import com.base.project.web.service.UserService;

@Controller
public class GreetingController {

	@Autowired
	private UserLoginFormValidator userLoginFormValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(userLoginFormValidator);
	}

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String login(User user) {
		return "login";
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("username", name);
		return "greeting";
	}

	@RequestMapping("/login")
	public String login(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		userService.login(user.getUsername(), user.getPassword(), user.getEmail());
		model.addAttribute("user", user);
		return "success";
	}

	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	@ResponseBody
	public User rest() {
		User u = new User();
		u.setUsername("1122");
		u.setPassword("3344");
		u.setEmail("5566@ask.me");
		return u;
	}

}
