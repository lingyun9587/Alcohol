package com.alcohol.controller;

import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UseraccountController {

    @Resource
    private UserAccountService userAccountService;


    @GetMapping( name = "/login")
    public String login(){
        return "cc";
    }
    @PostMapping( name="/login")
    public String login(HttpServletRequest request, @RequestParam( name = "username") String username, @RequestParam( name = "password") String password){
        userAccountService.login(username,password,request);
        return "index";
    }

}
