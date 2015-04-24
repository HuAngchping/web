package com.base.project.web.controllers;

import com.base.project.web.domain.CustomerLogin;
import com.base.project.web.domain.CustomerRegister;
import com.base.project.web.domain.validators.CustomerLoginValidator;
import com.base.project.web.domain.validators.CustomerRegisterValidator;
import com.base.project.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    private CustomerService customerService;

    @Autowired
    private CustomerRegisterValidator customerRegisterValidator;

    @Autowired
    private CustomerLoginValidator customerLoginValidator;

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
    public String weg_reg(@RequestParam(value = "login") String login_flag, CustomerRegister customerRegister, CustomerLogin customerLogin, Model model, HttpServletRequest request) {
        if (login_flag.equals("1")) {
            return "login";
        }

        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated CustomerLogin customerLogin, BindingResult bindingResult, Model model, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Cookie cookie = new Cookie("ticket", "ticket_localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setMaxAge(300);
        response.addCookie(cookie);
        model.addAttribute("customerLogin", customerLogin);
        return "redirect:" + customerLogin.getUrl();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Validated CustomerRegister customerRegister, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        customerService.createAccount(customerRegister);
        model.addAttribute("customerRegister", customerRegister);
        return "success_register";
    }

}
