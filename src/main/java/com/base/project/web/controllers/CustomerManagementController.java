package com.base.project.web.controllers;

import com.base.project.web.model.CustomerLogin;
import com.base.project.web.model.CustomerRegister;
import com.base.project.web.model.validators.CustomerLoginValidator;
import com.base.project.web.model.validators.CustomerRegisterValidator;
import com.base.project.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomerManagementController {

    @Autowired
    private CustomerRegisterValidator customerRegisterValidator;

    @Autowired
    private CustomerLoginValidator customerLoginValidator;
    @Autowired
    private CustomerService userService;

    @InitBinder(value = "customerLogin")
    private void initCustomerLoginBinder(WebDataBinder binder) {
        binder.addValidators(customerLoginValidator);
    }

    @InitBinder(value = "customerRegister")
    private void initCustomerRegisterBinder(WebDataBinder binder) {
        binder.addValidators(customerRegisterValidator);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/web_reg")
    public String weg_reg(@RequestParam(value = "login") String loginflag, CustomerRegister customerRegister, CustomerLogin customerLogin, HttpServletRequest request) {
        if (loginflag.equals("1")) {
            Cookie[] cookies = request.getCookies();
            return "login";
        }

        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated CustomerLogin customerLogin, BindingResult bindingResult, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        userService.login(customerLogin.getUsername(), customerLogin.getPassword());
        Cookie cookie = new Cookie("1", "2");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setMaxAge(300);
        response.addCookie(cookie);
        model.addAttribute("customerLogin", customerLogin);
        return "success_login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Validated CustomerRegister customerRegister, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.signup(customerRegister.getUsername(), customerRegister.getPassword(), customerRegister.getEmail());
        model.addAttribute("customerRegister", customerRegister);
        return "success_register";
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    @ResponseBody
    public CustomerRegister rest() {
        CustomerRegister u = new CustomerRegister();
        u.setUsername("1122");
        u.setPassword("3344");
        u.setEmail("5566@ask.me");
        return u;
    }

}
