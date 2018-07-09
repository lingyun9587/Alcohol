package com.alcohol.controller;

import com.alcohol.mapper.RktableMapper;
import com.alcohol.pojo.Rktable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/proc")
public class RktableController {
    @Resource
    private RktableMapper rktableMapper;

    @PostMapping(value="/listSell")
    @ResponseBody
    public Object listSell(@Param(value="productName")String productName){
        System.out.println("============================="+productName);
        List<Rktable> listRk=rktableMapper.SelAll(productName);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if(listRk.size()!=0){
            map.put("listRk",listRk);
        }
        return map;
    }
}
