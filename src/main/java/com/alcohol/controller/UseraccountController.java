package com.alcohol.controller;

import com.alcohol.cache.JedisUtil;
import com.alcohol.dto.UserAccountExecution;
import com.alcohol.exceptions.UserAccountOperationException;

import com.alcohol.pojo.ShopCart;
import com.alcohol.pojo.Sku;
import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserAccountService;
import com.alcohol.service.jms.ProducerCc;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UseraccountController {

    @Resource
    private UserAccountService userAccountService;
    private String phone;
    @Resource
    private JedisUtil.Hash jedisHashs;

    /*@Autowired
    Queue queue;
    @Autowired
    private ProducerCc producerCc;*/

    /*@RequestMapping("/rest")
    public  String index(){
        producerCc.sendMessage("123");
        return "123";
    }*/
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
    public Object login(HttpServletRequest request, HttpServletResponse response, @RequestParam( name = "username") String username, @RequestParam( name = "password") String password){

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
           map.put("msg","登陆失败！");
           return map;
       }

        if(user.getState() == 0){
            map.put("success",true);
            map.put("msg", user.getStateInfo());
            String userName=(String)SecurityUtils.getSubject().getPrincipal();
            Useraccount useraccount = userAccountService.getUserById(userName);
            User u=null;
            if(useraccount!=null){
                u=useraccount.getUser();
            }
            Map<String,String> mapmap=new HashMap<String,String>();
            // 定义jackson数据转换操作类
            ObjectMapper mapper = new ObjectMapper();
            Cookie[] c=request.getCookies();//获取cookie的所有数据

            if(c!=null){
                //遍历cookie
                label:for (Cookie cookie:c) {
                    String dateKey=new Date().toString().replace(":","").replace(" ","");
                    String keymapmap=u.getUserId().toString()+dateKey+IDUtil.getBianHao().toString();
                    if (cookie.getName().equals("JSESSIONID")) {
                        continue;
                    }
                    //解码
                    String strdecode = null;
                    try {
                        strdecode = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();
                    ShopCart scart = new ShopCart();
                    scart = gson.fromJson(strdecode, scart.getClass());
                    //查出redis的现有数据
                    Map<String, String> pp = jedisHashs.hgetAll(u.getUserId().toString());
                    for (String str : pp.keySet()) {
                        String obj = pp.get(str);
                        Gson gs = new Gson();
                        Map<String, Object> mk = new HashMap<String, Object>();
                        mk = gson.fromJson(obj, mk.getClass());
                        String jss = JSON.toJSONString(mk.get("sku"));
                        String jsss = JSON.toJSONString(mk.get("num"));
                        String i = new String();
                        i = gson.fromJson(jsss, i.getClass());
                        float ii = Float.parseFloat(i);
                        int iii = (int) ii;
                        Sku kk = new Sku();
                        kk = gson.fromJson(jss, kk.getClass());
                        if (kk.getSkuId() == scart.getSku().getSkuId()) {
                            int iiii = iii + scart.getNum();
                            mk.put("num", iiii);
                            try {
                                pp.put(str, mapper.writeValueAsString(mk));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }

                            jedisHashs.hmset(u.getUserId().toString(), pp);//保存到redis
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                            continue label;
                        }
                    }
                    /////////////
                    Map<String,Object> mapm=new HashMap<String,Object>();
                    mapm.put("sku",scart.getSku());//商品信息
                    mapm.put("num",scart.getNum());//用户选择商品的数量
                    mapm.put("img",scart.getImage());


                    try {
                        mapmap.put(keymapmap,mapper.writeValueAsString(mapm));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }


                }
                if(mapmap.size()!=0){
                    jedisHashs.hmset(u.getUserId().toString(),mapmap);//保存到redis
                }

            }
       }else{
            map.put("success",false);
            map.put("msg", user.getStateInfo());
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
     * @param
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

    /**
     * 后台登录
     */
    @ResponseBody
    @RequestMapping(value="seldeng",produces = "text/html;charset=utf-8")
    public String seldeng(Useraccount us){
        System.out.println(us.getPhone());
        phone=us.getPhone();
        System.out.println(phone);
        int count=userAccountService.seldeng(us);
        return JSON.toJSONString(count);
    }
    @ResponseBody
    @RequestMapping(value="cha",produces = "text/html;charset=utf-8")
    public String cha(){
        System.out.println(phone);
        return JSON.toJSONString(phone);
    }

}
