package com.alcohol.controller;

import com.alcohol.dto.OrderExecution;
import com.alcohol.pojo.*;
import com.alcohol.service.*;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.InteligentGeneralMerchantPromo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
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
    @Resource
    private UserAccountService userAccountService;
    @Resource
    private ProductService productService;
    @Resource
    private SkuService skuService;
    @Resource
    private SkuValueService skuValueService;
    /**
     * 添加订单信息
     * @return
     */
    @RequestMapping(value="/insertOrderInfo")
    public Object insertInfo(Order order)throws  Exception{
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        Long orderId = IDUtil.SnowflakeIdWorker();
        //创建订单对象
        Order order1 = new Order();
        order1.setOrderId(orderId);  //设置订单id
        order1.setCreateTime(new Date());  //设置订单创建时间
        order1.setUserId(useraccount.getUser().getUserId());  //设值用户
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
        for (Commodity commodity: order.getCommodities()) {
            Sku sku = commodity.getSku();
            String typevalueId=sku.getSkuvalueId();
            String [] arr = typevalueId.split(",");
            if(typevalueId !=null && !"".equals(typevalueId)){
                for (String str:arr) {
                    SkuValue skuvalue=skuValueService.getSkuById(Integer.parseInt(str));
                    commodity.getSku().getProduct().getSkuValues().add(skuvalue);
                }
            }

        }
      return order;
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

    /**
     * 结账处理
     * @return
     */
    @PostMapping(value = "/checkOutHandle")
    public Object checkOutHandle(){
        boolean flag = false;
        Map<String,Object> map = new HashMap<>();
        int result=0;
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName); //获取用户登陆信息
        Order order = orderService.getLastOrderInfo(useraccount.getUserId()); //获取单个订单信息
        order  = orderService.getById(order.getOrderId()); //获取订单中包含的全部信息
        order.setStatus(2);  //设置订单代发货
       try{
        orderService.updateOrderStatus(order);  //修改订单状态，同时修改销量'
        for (Commodity commodity:order.getCommodities()) {  //循环修改销量
             result =     productService.updatesales(commodity.getSku().getSkuId(),commodity.getNumber());  //修改销量
            skuService.updateInfo(commodity.getSku().getSkuId(),commodity.getNumber(),2); //删除锁定库存
            if(result>0){
                flag = true;
                continue;
            }else{
                flag = false;
                break;
            }
        }
       }catch (Exception e){
           map.put("success",false);

       }
      if(flag){
           map.put("success",true);
      }else{
          map.put("success",false);
      }
        return map;
    }
    /***
     * 销售额折线图
     * @param year
     * @return
     */
    @RequestMapping(value="yearMoney",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String yearMoney(@RequestParam(value = "year",required = false) int year){
        List<Order> qian=orderService.yearmoney(year);
        return JSON.toJSONString(qian);
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
                         @RequestParam(value = "product_name",required = false) String product_name,
                         @RequestParam(value = "phone",required = false) String phone,
                         @RequestParam(value = "orderStatus",required = false) Integer orderStatus) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("batch", batch);
        map.put("product_name", product_name);
        map.put("phone", phone);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("orderStatus", orderStatus);
        List<Order> list = orderService.order(map);
        for (int i = 0; i < list.size(); i++) {
            List<Commodity> commodity = list.get(i).getCommodities();//三个订单详情
            for (int j = 0; j < commodity.size(); j++) {//循环订单详情
                String skuvalueId = list.get(i).getCommodities().get(j).getSk().getSkuvalueId();//订单详情中的sku的value_id 3,5,6
                if(skuvalueId.length()>0) {
                    String[] arr = skuvalueId.split(",");
                    SkuValue skuvalue = null;
                    List<SkuValue> SkuValueList = new ArrayList<SkuValue>();
                    for (int x = 0; x < arr.length; x++) {
                        skuvalue = skuValueService.getSkuById(Integer.valueOf(arr[x]));
                        SkuValueList.add(skuvalue);
                    }
                    list.get(i).getCommodities().get(j).getSk().setSkuValueList(SkuValueList);
                }
            }
        }
        PageInfo<Order> page = new PageInfo<Order>(list);
        System.out.println(page.getTotal());
        System.out.println(list);
        return page;
    }


    /**
     * 查看订单详情 韩庆林
     * @param
     * @return
     */
    @RequestMapping(value="/backstage/cha")
    @ResponseBody
    public  Object cha(Long orderId ){

        Order order=orderService.cha(orderId);
        return JSON.toJSONString(order);
    }

    /**
     * 点击退款中，改变状态，变为已退款  韩庆林
     * @param order_id
     * @return
     */
    @RequestMapping(value ="/backstage/status")
    @ResponseBody
    public  String   status(Long order_id){
        int x=orderService.status(order_id);
        String json="";
        if(x>0){
            json="{\"result\":\"yes\",\"mes\":\"退款成功！\"}";
        }else{
            json="{\"result\":\"no\",\"mes\":\"退款失败！\"}";
        }
        return  json;
    }

    @RequestMapping(value ="/backstage/fa")
    @ResponseBody
    public  String   fa(Long orderid){
        int x=orderService.fa(orderid);
        String json="";
        if(x>0){
            json="{\"result\":\"yes\",\"mes\":\"发货成功！\"}";
        }else{
            json="{\"result\":\"no\",\"mes\":\"发货失败！\"}";
        }
        return  json;
    }
}
