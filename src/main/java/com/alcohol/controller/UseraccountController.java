package com.alcohol.controller;

import com.alcohol.dto.UserAccountExecution;
import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserAccountService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UseraccountController {

    @Resource
    private UserAccountService userAccountService;

    @PostMapping( value="/registerUser")
    public Object register(@RequestParam(value = "username" , required = false)String username,@RequestParam( value = "password",required = false)String password){
        Map<String,Object> map = new HashMap<String,Object>();
        if(username == null || password == null){
            map.put("success",true);
            map.put("msg","手机号或密码不能为空");
        }
        UserAccountExecution userAccountExecution =  userAccountService.register(username,password);
       if(userAccountExecution.getState() == 0){
           map.put("success",true);
           map.put("msg",userAccountExecution.getStateInfo());
       }else{
           map.put("success",false);
           map.put("msg",userAccountExecution.getStateInfo());
       }
       return map;
    }
    /**
     * 用户登陆
     * @param request
     * @param username
     * @param password
     * @return
     */
    @PostMapping( value="/user/loginUser")
    public Object login(HttpServletRequest request, @RequestParam( name = "username") String username, @RequestParam( name = "password") String password){
        Map<String,Object> map = new HashMap<String,Object>();
        UserAccountExecution  user=null;
        Md5Hash md5 = new Md5Hash(password);
       try{
            user = userAccountService.login(username,md5.toString(),request);
       }catch(UserAccountOperationException e){
           map.put("success",false);
           map.put("msg",e.toString());
           return map;
       }

        if(user.getState() == 0){
            map.put("success",true);
            map.put("msg", user.getState());
        }else{
            map.put("success",false);
            map.put("msg", user.getState());
        }
        return map;
    }
}
