package com.alcohol.controller;

import com.alcohol.cache.JedisPoolWriper;
import com.alcohol.cache.JedisUtil;
import com.alcohol.config.redis.RedisConfiguration;
import com.alcohol.dto.OrderExecution;
import com.alcohol.pojo.*;
import com.alcohol.service.ImageService;
import com.alcohol.service.OrderService;
import com.alcohol.service.ProductService;
import com.alcohol.service.UserAccountService;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * 购物车
 */
@Controller
@RequestMapping("/shop")
public class ShopCartController {
    @Resource
    private ProductService productService;
    @Resource
    private ImageService imageService;
    @Resource
    private JedisUtil.Hash jedisHashs;
    @Resource
    private OrderService orderService;
    @Resource
    private UserAccountService userAccountService;

    /*@Resource
    private JedisUtil.Keys jedisKeys;
    @Resource
    private JedisUtil.Strings jedisStrings;*/

    /**
     * 添加购物车
     * @return
     */
    @GetMapping(value = "/addShopCart")
    @ResponseBody
    public String addShopCart(/*@RequestParam(value = "productId",required = false)Long productId,
                              @RequestParam(value = "skuId",required = false)Long skuId,
                              @RequestParam(value = "num",required = false)int num,*/
                              HttpServletRequest request, HttpServletResponse response, HttpSession session)throws  Exception{

// 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;

       Long productId=2L;
       Long skuId=4L;
       int num=5;
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
         request.getSession().setAttribute("user",u);//向session中保存数据
         User user=(User) session.getAttribute("user");//获取session中的数据

        Image img=imageService.selImageByProductId(productId);
        StringBuffer sb=new StringBuffer();
        sb.append(productId);
        sb.append("#");//使用逗号分开，以便取值
        sb.append(skuId);
        sb.append("#");
        String str1= sb.append(num).toString();
        //按照商品id和skuid查询出对应的sku和商品信息
        Sku sku= null;//productService.getProductBySkuIdAndProductId(productId,skuId);
         if(user==null){//如果为null，证明没有登录，操作cookie
             Cookie[] c=request.getCookies();//获取cookie的所有数据
            //遍历现有cookie数据
             if(c!=null){
                 for (Cookie cookie : c) {
                  /*cookie.setMaxAge(0);
                     response.addCookie(cookie);*/
                     //分割cookie值，取出分别的值
                     String[] s=cookie.getValue().toString().split("#");
                     //要添加的商品sku和cookie中已经有的商品sku是否一样
                         if(s[0].equals(productId.toString()) && s[1].equals(skuId.toString())){
                            //证明有相同商品，修改商品数量
                            //
                             return "";//方法结束cookie修改
                         }
                 }
                 //走到这证明没有在现有cookie中找到同样的商品，所以添加到cookie
                 Cookie cc=new Cookie("c7",str1);
                 response.addCookie(cc);
             }
            return JSON.toJSONString("添加成功");//方法结束cookie添加
         }else{//证明已经登录，操作redis
             //查询出商品信息
            sku=productService.selectProductBySkuIdAndProductId(productId,skuId);
             String dateKey=new Date().toString();
             String keymapmap=u.getUserId().toString()+dateKey;
             Image im=null;
             //将商品信息和用户选择的数量保存到redis
             //判断是否有这个用户
             if(jedisHashs.hexists(u.getUserId().toString())){
                 //有的话就操作这个用户的key-value
                 //查出redis的现有数据
                 Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());

                 for (String str:pp.keySet()) {
                    String obj=pp.get(str);
                     Gson gson=new Gson();
                     Map<String,Object> mk=new HashMap<String,Object>();
                     mk=gson.fromJson(obj,mk.getClass());
                     String jss=JSON.toJSONString(mk.get("sku"));
                     String jsss=JSON.toJSONString(mk.get("num"));
                     String i=new String();
                     i=gson.fromJson(jsss,i.getClass());
                     float ii=Float.parseFloat(i);
                     int iii=(int)ii;
                     Sku kk=new Sku();
                     kk=gson.fromJson(jss,kk.getClass());
                     if(kk.getSkuId()==skuId){
                         int iiii=iii+num;
                         mk.put("num",iiii);
                         pp.put(str,mapper.writeValueAsString(mk));
                         jedisHashs.hmset(user.getUserId().toString(),pp);//保存到redis
                         return JSON.toJSONString("该商品已在购物车，数量改变");
                     }
                 }
                 Map<String,String> mp=new HashMap<String,String>();
                 //证明改用户没有该商品，新增一条
                 Map<String,Object> map=new HashMap<String,Object>();
                 map.put("sku",sku);//商品信息
                // map.put("user",user);//保存用户信息，确认是哪个用户的购物车
                 map.put("num",num);//用户选择商品的数量
                 map.put("img",img);
                 mp.put(keymapmap,mapper.writeValueAsString(map));
                 jedisHashs.hmset(user.getUserId().toString(),mp);//保存到redis
                return JSON.toJSONString("添加成功");
             }else{
                //没有的话新建key-value
                 Map<String,Object> map=new HashMap<String,Object>();
                 map.put("sku",sku);//商品信息
                // map.put("user",user);//保存用户信息，确认是哪个用户的购物车
                 map.put("num",num);//用户选择商品的数量
                 map.put("img",img);
                 Map<String,String> mapmap=new HashMap<String,String>();
                 mapmap.put(keymapmap,mapper.writeValueAsString(map));
                jedisHashs.hmset(user.getUserId().toString(),mapmap);//保存到redis
                 return JSON.toJSONString("添加成功");//方法结束，redis添加，修改
             }
         }
    }

    /**
     * 查询购物车
     * @return
     */
    @GetMapping(value = "/selshopAll")
    @ResponseBody
    public String getShopCart(){
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
        String dateKey=new Date().toString();
        String keymapmap=u.getUserId().toString()+dateKey;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        List<ShopCart> shopCartList=new ArrayList<ShopCart>();
        if(u==null){
            //查询cookie
            return "";
        }else{
            //查询redis
            Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());
            //Map<String,Object> mp=new HashMap<String,Object>();
            for (String str:pp.keySet()) {
                String obj=pp.get(str);
                Gson gson=new Gson();
                Map<String,Object> mk=new HashMap<String,Object>();
                mk=gson.fromJson(obj,mk.getClass());
                String jss=JSON.toJSONString(mk.get("sku"));
                Sku kk=new Sku();
                kk=gson.fromJson(jss,kk.getClass());
                //Map<String,Object> m2=new HashMap<String,Object>();
               // m2.put("sku",kk);

                String jsss=JSON.toJSONString(mk.get("num"));
                String i=new String();
                i=gson.fromJson(jsss,i.getClass());
                float ii=Float.parseFloat(i);
                int iii=(int)ii;
                //m2.put("num",iii);
                ShopCart shopCart=new ShopCart();
                shopCart.setSku(kk);
                shopCart.setNum(iii);
                shopCart.setImage(imageService.selImageByProductId(kk.getProduct().getProductId()));
                shopCartList.add(shopCart);
                //mp.put(str,m2);
            }
            return JSON.toJSONString(shopCartList);
        }
    }
    /**
     * 删除购物车
     * @return
     */
    @GetMapping(value = "/delShop")
    @ResponseBody
    public String delShop(@RequestParam("skuId")Long skuId){
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
        //查询redis
        Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());
        Iterator<String> it=pp.keySet().iterator();
        while(it.hasNext()){
            String key=it.next();
            String obj=pp.get(key);
            Gson gson=new Gson();
            Map<String,Object> mk=new HashMap<String,Object>();
            mk=gson.fromJson(obj,mk.getClass());
            String jss=JSON.toJSONString(mk.get("sku"));
            Sku kk=new Sku();
            kk=gson.fromJson(jss,kk.getClass());
            if(kk.getSkuId()==skuId){
                it.remove();
            }
        }
        jedisHashs.hdel(u.getUserId().toString());
        if(pp.size()!=0) {
            jedisHashs.hmset(u.getUserId().toString(), pp);//保存到redis
        }
        return JSON.toJSONString(1);
    }
    /**
     * 加减购物车
     * @return
     */
    @GetMapping(value = "/numPlusOrReduce")
    @ResponseBody
    public String numPlusOrReduce(@RequestParam("skuId")Long skuId, @RequestParam("num")int num){
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
        //查出redis的现有数据
        Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());

        for (String str:pp.keySet()) {
            String obj=pp.get(str);
            Gson gson=new Gson();
            Map<String,Object> mk=new HashMap<String,Object>();
            mk=gson.fromJson(obj,mk.getClass());
            String jss=JSON.toJSONString(mk.get("sku"));
            String jsss=JSON.toJSONString(mk.get("num"));
            String i=new String();
            i=gson.fromJson(jsss,i.getClass());
            float ii=Float.parseFloat(i);
            int iii=(int)ii;
            Sku kk=new Sku();
            kk=gson.fromJson(jss,kk.getClass());
            if(kk.getSkuId()==skuId){
                int iiii=num;
                mk.put("num",iiii);
                try {
                    pp.put(str,mapper.writeValueAsString(mk));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                jedisHashs.hmset(u.getUserId().toString(),pp);//保存到redis
                return JSON.toJSONString("购物车页面修改数量成功");
            }
        }
        return "";
    }
    /**
     * 向订单模块传输所需数据
     * @return
     */
    @RequestMapping(value = "/transferToOrder")
    public Object transferToOrder(@RequestParam(value = "productOrders",required = false)Long[] productOrders){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
        String dateKey=new Date().toString();
        String keymapmap=u.getUserId().toString()+dateKey;
        //查出redis的现有数据
        Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());

        //保存要提交订单的购物车上牌信息
        Map<String,String> mmm=new HashMap<String,String>();
        for (int i=0;i<productOrders.length;i++){
            if(productOrders[i]!=null){

                Iterator<String> it=pp.keySet().iterator();
                while(it.hasNext()){
                    String key=it.next();
                    String obj=pp.get(key);
                    Gson gson=new Gson();
                    Map<String,Object> mk=new HashMap<String,Object>();
                    mk=gson.fromJson(obj,mk.getClass());
                    String jss=JSON.toJSONString(mk.get("sku"));
                    Sku kk=new Sku();
                    kk=gson.fromJson(jss,kk.getClass());
                    if(kk.getSkuId()==productOrders[i]){
                        mmm.put(key,pp.get(key));
                        it.remove();
                    }
                }

            }
        }
        jedisHashs.hdel(u.getUserId().toString());
            jedisHashs.hmset(u.getUserId().toString(),pp);//保存到redis

        User user = new User();   //订单对象
        user.setUserId(useraccount.getUserId());
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
        int number = 0;  //总数数量
        double money = 0;  //总金额
        for (String str: mmm.keySet()) {
            String so =  mmm.get(str);
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
        return  map; //返回信息
    }
}
