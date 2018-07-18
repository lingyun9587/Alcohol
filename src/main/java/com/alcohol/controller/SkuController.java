package com.alcohol.controller;

import com.alcohol.cache.JedisUtil;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.ShopCart;
import com.alcohol.pojo.Sku;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.SkuService;
import com.alcohol.service.UserAccountService;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SkuController {

    @Resource
    private SkuService skuService;
    @Resource
    private JedisUtil.Hash jedisHashs;
    @Resource
    private UserAccountService userAccountService;
    @RequestMapping(value = "/getByIdskuInfo")
    public Object getById(@RequestParam("id")Long id){
        Sku sku = skuService.getById(id);
        return sku;
    }

    /**
     * 获取所有用户信息
     * @return
     */
    @RequestMapping(value = "/listSkuOrderInfo")
    public Object listInfo(){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        Map<String,String> pp= jedisHashs.hgetAll(useraccount.getUserId()+"order");
        //接收商品信息
        List<Commodity> commodities = new ArrayList<>();  //商品
        for (String str: pp.keySet()) {
            String so =  pp.get(str);
            Gson gson = new Gson();
            Map<String,Object> map12 = new HashMap<>();
            map12 = gson.fromJson(so,map12.getClass());
            String s = new String();
            String s1 =  JSON.toJSONString(map12.get("num"));
            s = gson.fromJson(s1,s.getClass());
            float f =Float.parseFloat(s);
            int num = (int)f;   //获取到数量
            Sku sku = new Sku(); //获取sku对象
            sku = gson.fromJson(JSON.toJSONString(map12.get("sku")),sku.getClass());
            //
            Commodity commodity = new Commodity();
            commodity.setNumber(num);   //数量
            commodity.setSkuId(sku.getSkuId());  //skuId
            commodity.setSku(skuService.getById(sku.getSkuId()));
            commodities.add(commodity);
        }
        return commodities;
    }
}
