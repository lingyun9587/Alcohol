package com.alcohol.controller;

import com.alcohol.service.OrderstatusService;
import com.alcohol.vo.OrderstatusVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderstatusController {

    @Resource
    private OrderstatusService orderstatusService;

    @GetMapping(value = "/listOrderStatusByUserId")
    public Object listOrderStatusByUserId(){
        Long userId = 1L;  //从作用域中获取对象编号
        List<OrderstatusVo> list = orderstatusService.listByUserId(userId);
        return list;
    }
}
