package com.alcohol.controller;

import com.alcohol.pojo.Address;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.AddressService;
import com.alcohol.service.UserAccountService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址控制层
 */
@Controller
public class AddressController {

    @Resource
    private AddressService addressService;
    @Resource
    private UserAccountService userAccountService;


    @PostMapping(value = "/insAdd")
    @ResponseBody
    public Object insAdd(HttpSession session, Address address) {
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        address.setUserId(useraccount.getUserId());
        int er = addressService.insertAddress(address);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (er != 0) {
            map.put("mes", "yes");
            map.put("mesage", "添加收货地址成功!");
        } else {
            map.put("mes", "no");
            map.put("mesage", "网络错误,添加收货地址失败!");
        }
        return map;
    }

    @PostMapping("/upAdd")
    @ResponseBody
    public Object upAdd(HttpSession session, Address address) {
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        address.setUserId(useraccount.getUserId());
        int er = addressService.upAdd(address);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (er != 0) {
            map.put("mes", "yes");
        } else {
            map.put("mes", "no");
        }
        return map;
    }

    @PostMapping("/listAdd")
    @ResponseBody
    public Object listaAdd(HttpSession session) {
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        List<Address> listAdd = addressService.listAdd(useraccount.getUserId());
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("listAdd",listAdd);
        return map;
    }

    @PostMapping("/updaAdd")
    @ResponseBody
    public Object upAdd(HttpSession session,Long addressId) {
        Address address=addressService.SelAdd(addressId);
        return JSON.toJSONString(address);
    }
    @PostMapping("/updateAdd")
    @ResponseBody
    public Object upteAdd(HttpSession session,Address address) {
        int er = addressService.updataAdd(address);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (er != 0) {
            map.put("mes", "yes");
            map.put("mesage", "修改成功!");
        } else {
            map.put("mes", "no");
            map.put("mesage", "修改失败,请联系管理员!");
        }
        return map;
    }

    @PostMapping("/delAdd")
    @ResponseBody
    public Object dalAdd(HttpSession session,Long addressId) {
        int er = addressService.delAdd(addressId);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (er != 0) {
            map.put("mes", "yes");
            map.put("mesage", "删除成功!");
        } else {
            map.put("mes", "no");
            map.put("mesage", "删除失败,请联系管理员!");
        }
        return map;
    }
    @PostMapping(value="/upMoAdd")
    @ResponseBody
    public Object upMoAdd(Long addressId,Address address){
        Map<Object, Object> map = new HashMap<Object, Object>();
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        address.setUserId(useraccount.getUserId());
        int er = addressService.upAdd(address);
        if (er != 0) {
            int er2=addressService.upMoAdd(addressId);
            if(er2!=0){
                map.put("mes", "yes");
                map.put("mesage", "设置默认地址成功!!");
            }
        } else {
            map.put("mes", "no");
            map.put("mesage", "设置失败,请联系管理员!");
        }
        return map;
    }
}
