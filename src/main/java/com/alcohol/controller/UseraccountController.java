package com.alcohol.controller;

import com.alcohol.cache.JedisUtil;
import com.alcohol.dto.UserAccountExecution;
import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.jms.ConsumerCc;
import com.alcohol.jms.ProducerCc;
import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UseraccountController {

    @Resource
    private UserAccountService userAccountService;


    @Autowired
    Queue queue;
    @Autowired
    private ProducerCc producerCc;

    @RequestMapping("/rest")
    public  String index(){
        producerCc.sendMessage("123");
        return "123";
    }
    @PostMapping( value="/registerUser")
    public Object register(HttpServletRequest request,@RequestParam(value = "username" , required = false)String username,@RequestParam( value = "password",required = false)String password){
        Map<String,Object> map = new HashMap<String,Object>();
        if(username == null || password == null){
            map.put("success",true);
            map.put("msg","手机号或密码不能为空");
        }
        UserAccountExecution userAccountExecution = null;
        try{
             userAccountExecution =  userAccountService.register(username,password);
        }catch(Exception e){

            map.put("success",false);
            map.put("msg",e.toString());
            return map;
        }
       if(userAccountExecution.getState() == 0){
           UserAccountExecution  user=null;
           Md5Hash md5 = new Md5Hash(password);
           user = userAccountService.login(username,md5.toString(),request);
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
            if(user.getState() ==0){
                map.put("success",true);
                map.put("msg",user.getStateInfo());
            }else{
                map.put("success",false);
                map.put("msg",user.getStateInfo());
            }
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

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping( value = "/getUserInfo")
    private Object getUserId(){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        return useraccount;
    }

    /**
     * 修改用户信息
     * @param useraccount
     * @return
     */
    @RequestMapping( value = "/udai_updateUser")
    public Object updateInfo(@RequestParam(value = "membershipName",required = false)String membershipName,
                             @RequestParam(value = "nickName",required = false)String nickName,
                             @RequestParam(value = "sex",required = false)char  sex,
                             @RequestParam(value = "realName",required = false)String  realName,
                             @RequestParam(value = "email",required = false)String email
                             ){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount1 = userAccountService.getUserById(userName);
        Useraccount useraccount=new Useraccount();
        useraccount.setUserId(useraccount1.getUserId());
        useraccount.setEmail(email);
        User user = new User();
        user.setUserId(useraccount1.getUserId());
        user.setNickName(nickName);
      /*  user.setBirthday(birthday);
                             @RequestParam(value = "birthday",required = false)Date birthday,*/
        user.setRealName(realName);
        user.setSex(sex);
        useraccount.setUser(user);
        Map<String,Object> map = new HashMap<String ,Object>();
        UserAccountExecution userAccountExecution = null;
       try{
           userAccountExecution = userAccountService.updateInfo(useraccount);
           if(userAccountExecution.getState() == 0){
               map.put("success",true);
               map.put("msg",userAccountExecution.getStateInfo());
           }else{
               map.put("success",false);
               map.put("msg",userAccountExecution.getStateInfo());
           }
       }catch (Exception e){
           map.put("success",false);
           map.put("msg",e.toString());
       }
      return map;
    }

    /**
     * 判断原密码
     * @param session
     * @return
     */
    @PostMapping( value="/updatePwd")
    @ResponseBody
    public Object UpdatePwd(HttpSession session,String opwd){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        Md5Hash md5 = new Md5Hash(opwd);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if(!useraccount.getPassword().equals(md5.toString())){
            map.put("mes","no");
            map.put("mesage","密码输入有误，请重新输入！");
        }else{
            map.put("mes","yes");
        }
        return map;
    }

    /**
     * 修改密码
     */
    @PostMapping(value = "/upPwd")
    @ResponseBody
    public Object upPwd(HttpSession session, String npwd){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        Md5Hash md5 = new Md5Hash(npwd);
        useraccount.setPassword(md5.toString());
        int er=userAccountService.updatePwd(useraccount);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (er!=0){
            map.put("mes","yes");
        }else{
            map.put("mes","no");
            map.put("mesage","密码修改失败，请重新输入。");
        }
        return map;
    }

}
