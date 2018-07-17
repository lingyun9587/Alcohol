package com.alcohol.controller;

import com.alcohol.pojo.Useraccount;
import com.alcohol.service.OrderstatusService;
import com.alcohol.service.UserAccountService;
import com.alcohol.vo.OrderstatusVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderstatusController {

    @Resource
    private OrderstatusService orderstatusService;
    @Resource
    private UserAccountService userAccountService;

    @GetMapping(value = "/listOrderStatusByUserId")
    public Object listOrderStatusByUserId(){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        Long userId = useraccount.getUser().getUserId();  //从作用域中获取对象编号
        List<OrderstatusVo> list = orderstatusService.listByUserId(userId);
        return list;
    }
}
