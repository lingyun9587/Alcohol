package com.alcohol.controller;

import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class UserController {
    @Resource
    private UserService userService;
@RequestMapping(value = "/zhuce",produces ="application/json;charset=utf-8" )
@ResponseBody
    public String zhu(Useraccount useraccount){
    Map<String,Object> li=new HashMap<String,Object>();
    boolean fa=userService.ZhuCe(useraccount);
    li.put("fa",fa);
    return JSON.toJSONString(li);
}
}
