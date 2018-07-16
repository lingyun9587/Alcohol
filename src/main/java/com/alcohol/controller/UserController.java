package com.alcohol.controller;

import com.alcohol.pojo.User;
import com.alcohol.service.UserService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    private List<User> listUser;

    @RequestMapping(value ="/backstage/userListInfo.html")
    private String userList(){
        return "/backstage/userList";
    }

    @ResponseBody
    @RequestMapping(value = "/listUser",method = RequestMethod.GET)
    public Object listUser(User user,
                           @RequestParam(value="nickName",required = false)String nickName,
                           @RequestParam(value = "pageNum", required = false) Integer pageNum,
                           @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        listUser = userService.listUser(nickName,pageNum,pageSize);
        PageInfo<User> page = new PageInfo<User>(listUser);
        System.out.println("昵称名："+nickName);
        System.out.println("数据总数:" + page.getTotal());
        System.out.println("数据总页数:" + page.getPages());
        return JSON.toJSONString(page);
    }

    @ResponseBody
    @RequestMapping(value="/updateStatus")
    private Object updateStatus(@RequestParam(value = "userId", required = false)Long userId,
                                @RequestParam(value = "frozen", required = false)Long frozen){
        Map<String,Object> map = new HashMap<>();
        System.out.println("userId:"+userId);
        System.out.println("frozen:"+frozen);
            int result=userService.updStatus(userId,frozen);
            if(result>0){
                map.put("success",true);
                map.put("mess","操作成功！");
            }else{
                map.put("success",false);
                map.put("mess","操作失败！");
            }
            return map;
    }


}
