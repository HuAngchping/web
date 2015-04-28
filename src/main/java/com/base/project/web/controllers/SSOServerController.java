package com.base.project.web.controllers;

import com.base.project.web.domain.CustomerLogin;
import com.base.project.web.domain.CustomerRegister;
import com.base.project.web.utils.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SSOServerController {

    @RequestMapping(value = "/is_login", method = RequestMethod.GET)
    public String is_login(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response, CustomerLogin customerLogin) {
        String ticket = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("ticket")) {
                    ticket = c.getValue();
                    break;
                }
            }
        }
        if (StringUtils.isEmpty(ticket)) {
            String nonce = MyUtils.getNonceTicket();
            customerLogin.setNonce(nonce);
            customerLogin.setUrl(url);
            return "login";
        }
        return "redirect:" + url;
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
