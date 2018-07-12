package com.alcohol.controller;

import com.alcohol.dto.OrderExecution;
import com.alcohol.pojo.*;
import com.alcohol.service.OrderService;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.InteligentGeneralMerchantPromo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 订单
 */
@RestController
public class OrderController{


    @Resource
    private OrderService orderService;

    /**
     * 添加订单信息
     * @return
     */
    @RequestMapping(value="/insertOrderInfo")
    public Object insertInfo(Order order)throws  Exception{
        User user = new User();   //订单对象
        user.setUserId(1L);
        Long orderId = IDUtil.SnowflakeIdWorker();
        //创建订单对象
        Order order1 = new Order();
        order1.setOrderId(orderId);  //设置订单id
        order1.setCreateTime(new Date());  //设置订单创建时间
        order1.setUserId(user.getUserId());  //设值用户
        order1.setStatus(1);   //设置订单状态
        order1.setAddressId(1L);  //设置地址id  测试
        //接收商品信息
        List<Commodity> commodities = new ArrayList<>();  //商铺下的商品

        Map<String,String> mapshopping = new HashMap<>();  //购物车信息
        Map<String,Object> qw1 = new HashMap<String,Object>();
        Sku sku1 = new Sku();
        sku1.setSkuId(12L);
        qw1.put("sku",sku1);
        qw1.put("num",12);
        Map<String,Object> qw2 = new HashMap<String,Object>();
        Sku sku2 = new Sku();
        sku2.setSkuId(11L);
        qw2.put("sku",sku2);
        qw2.put("num",12);

        ObjectMapper objectMapper = new ObjectMapper();

        mapshopping.put("123",objectMapper.writeValueAsString(qw1));
        mapshopping.put("1234",objectMapper.writeValueAsString(qw2));

        int number = 0;  //总数数量
        double money = 0;  //总金额
        for (String str: mapshopping.keySet()) {
           String so =  mapshopping.get(str);
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
            Product product = new Product();
            product.setProductId(1L);
            sku.setProduct(product);
          //添加订单商品信息
            long commodityID = IDUtil.SnowflakeIdWorker();
            Commodity commodity = new Commodity();
            commodity.setCommodityId(commodityID);  //详情id
            commodity.setOrderShopId(sku.getProduct().getUserId());  //商品id
            commodity.setOrderId(orderId);  //订单id
            commodity.setNumber(num);   //数量
            commodity.setSkuId(sku.getSkuId());  //skuId
            commodity.setOrderstatusId(1L);  //1待付款
            commodities.add(commodity);  //添加到订单信息
            number =number+num;   //数量
            money =money+(num*sku.getPresentPrice());  //金额
        }
        order1.setCommodities(commodities);
        order1.setGoodsCount(number);  //设置总数量
        order1.setMoney(money);    //设置总金额
        Map<String,Object> map = new HashMap<>();
        OrderExecution orderExecution;
        try{
            orderExecution = orderService.insertInfo(order1);
            if(orderExecution.getState() == 0 ){
                map.put("success",true);
                map.put("msg",orderExecution.getStateInfo());
            }else{
                map.put("success",false);
                map.put("msg",orderExecution.getStateInfo());
            }
        }catch(Exception e){
            map.put("success",false);
            map.put("msg",e.toString());
        }
        return  map;

    }

    @RequestMapping("/getByIdOrderInfo")
    public Object getById(@RequestParam(value = "id",required = false)Long id){
      Order order= orderService.getById(id) ;
      return order;
    }

    /**
     * 查看订单 韩庆林
     * @param pageNum
     * @param pageSize
     * @param batch
     * @param orderStatus
     * @return
     */
    @RequestMapping(value="/backstage/order")
    @ResponseBody
    public  Object order(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                         @RequestParam(value = "pageSize",required = false) Integer pageSize,
                         @RequestParam(value = "batch",required = false) String batch,
                         @RequestParam(value = "orderStatus",required = false) Integer orderStatus){

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("batch",batch);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("orderStatus",orderStatus);
        List<Order> list=orderService.order(map);
        PageInfo<Order> page=new PageInfo<Order>(list);
        System.out.println(page.getTotal());
        System.out.println(list);
        return page;
    }

    /**
     * 查看订单详情 韩庆林
     * @param order_id
     * @return
     */
    @RequestMapping(value="/backstage/cha")
    @ResponseBody
    public  Object cha(@RequestParam(value = "order_id",required = false) Integer order_id){
        Order order=orderService.cha(order_id);
        return JSON.toJSONString(order);
    }

    /**
     * 点击退款中，改变状态，变为已退款  韩庆林
     * @param order_id
     * @return
     */
    @RequestMapping(value ="/backstage/status")
    @ResponseBody
    public  String   status(int order_id){
        int x=orderService.status(order_id);
        String json="";
        if(x>0){
            json="{\"result\":\"yes\",\"mes\":\"退款成功！\"}";
        }else{
            json="{\"result\":\"no\",\"mes\":\"退款失败！\"}";
        }
        return  json;
    }


    @RequestMapping(value = "/updateOrderStatus")
    public Object updateInfo(@RequestParam("id")Long id,@RequestParam("status")Integer status){
        Order order  = new Order();
        order.setOrderId(id);
        order.setStatus(status);
        Map<String,Object> map = new HashMap<>();
        OrderExecution orderExecution = null;
        try {
            orderExecution = orderService.updateOrderStatus(order);
            if(orderExecution.getState() == 0){
                map.put("success",true);
                map.put("msg",orderExecution.getStateInfo());
            }else{
                map.put("success",false);
                map.put("msg",orderExecution.getStateInfo());
            }
        }catch (Exception e){
            map.put("success",false);
            map.put("msg",e.toString());
        }
        return map;
    }
}
