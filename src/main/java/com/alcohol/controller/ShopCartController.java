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
import com.sun.deploy.net.URLEncoder;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @PostMapping(value = "/addShopCart")
    @ResponseBody
    public String addShopCart(@RequestParam(value = "skuid",required = false)Integer skuid,
                              @RequestParam(value = "num",required = false)int num,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session)throws  Exception{
        Long skuId=Long.valueOf(skuid);
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        Integer productid=(Integer)request.getSession().getAttribute("productId");
        Long productId=(long)productid;
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        User u=null;
        if(useraccount!=null){
            u=useraccount.getUser();
        }

        Image img=imageService.selImageByProductId(productId);
        //查询出商品信息
        Sku sku=productService.selectProductBySkuIdAndProductId(productId,skuId);
        //创建购物车对象
        ShopCart sc=new ShopCart();
        sc.setSku(sku);
        sc.setImage(img);
        sc.setNum(num);
        String dateKey=new Date().toString().replace(":","").replace(" ","");

         if(u==null){//如果为null，证明没有登录，操作cookie
             Cookie[] c=request.getCookies();//获取cookie的所有数据
             //Cookie[] c=null;

            //遍历现有cookie数据
             if(c!=null){
                 for (Cookie cookie : c) {
                     if(cookie.getName().equals("JSESSIONID")){
                        continue;
                     }
                     //解码
                     String strdecode=URLDecoder.decode(cookie.getValue(),"utf-8");
                     Gson gson=new Gson();
                     ShopCart scart=new ShopCart();
                     scart=gson.fromJson(strdecode,scart.getClass());
                     //要添加的商品sku和cookie中已经有的商品sku是否一样
                     if(scart.getSku().getSkuId()==sc.getSku().getSkuId()){
                        //进来证明cookie中有这条skuid的商品，修改该skuid商品的数量
                         cookie.setMaxAge(0);//设置存活时间为0，也就是删除
                         cookie.setPath("/");
                         int n=scart.getNum();
                         int nn=n+sc.getNum();
                         scart.setNum(nn);
                         //编码
                         String _shopCartEncode=URLEncoder.encode(JSON.toJSONString(scart), "utf-8");
                         Cookie ccc=new Cookie(cookie.getName(),_shopCartEncode);
                         ccc.setPath("/");
                         response.addCookie(ccc);
                         return JSON.toJSONString("修改数量成功");//方法结束cookie修改数量
                     }
                 }
             }
             String shopcartJson=JSON.toJSONString(sc);
             //编码
             String shopCartEncode=URLEncoder.encode(shopcartJson, "utf-8");
             //证明cookie没有信息或者没有找到同样skuid的信息，直接添加
             Cookie cc=new Cookie(dateKey,shopCartEncode);
             cc.setPath("/");
             response.addCookie(cc);
            return JSON.toJSONString("添加成功");//方法结束cookie添加
         }else{//证明已经登录，操作redis
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
                         jedisHashs.hmset(u.getUserId().toString(),pp);//保存到redis
                         return JSON.toJSONString("该商品已在购物车，数量改变");
                     }
                 }
                 Map<String,String> mp=new HashMap<String,String>();
                 //证明改用户没有该商品，新增一条
                 Map<String,Object> map=new HashMap<String,Object>();
                 map.put("sku",sku);//商品信息
                 map.put("num",num);//用户选择商品的数量
                 map.put("img",img);
                 mp.put(keymapmap,mapper.writeValueAsString(map));
                 jedisHashs.hmset(u.getUserId().toString(),mp);//保存到redis
                return JSON.toJSONString("添加成功");
             }else{
                //没有的话新建key-value
                 Map<String,Object> map=new HashMap<String,Object>();
                 map.put("sku",sku);//商品信息
                 map.put("num",num);//用户选择商品的数量
                 map.put("img",img);
                 Map<String,String> mapmap=new HashMap<String,String>();
                 mapmap.put(keymapmap,mapper.writeValueAsString(map));
                jedisHashs.hmset(u.getUserId().toString(),mapmap);//保存到redis
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
    public String getShopCart(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        User u=null;
        if(useraccount!=null){
            u=useraccount.getUser();
        }
        String dateKey=new Date().toString().replace(":","").replace(" ","");

        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        List<ShopCart> shopCartList=new ArrayList<ShopCart>();
        if(u==null){
            //查询cookie
            Cookie[] c=request.getCookies();//获取cookie的所有数据
            if(c!=null){
                for (Cookie cookie : c) {
                    if(cookie.getName().equals("JSESSIONID")){
                        continue;
                    }
                    //解码
                    String strdecode= null;
                    try {
                        strdecode = URLDecoder.decode(cookie.getValue(),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    cookie.setMaxAge(0);
                    Gson gson=new Gson();
                    ShopCart scart=new ShopCart();
                    scart=gson.fromJson(strdecode,scart.getClass());
                    shopCartList.add(scart);
                }
            }
        }else{
            String keymapmap=u.getUserId().toString()+dateKey;
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
                String jsss=JSON.toJSONString(mk.get("num"));
                String i=new String();
                i=gson.fromJson(jsss,i.getClass());
                float ii=Float.parseFloat(i);
                int iii=(int)ii;
                ShopCart shopCart=new ShopCart();
                shopCart.setSku(kk);
                shopCart.setNum(iii);
                shopCart.setImage(imageService.selImageByProductId(kk.getProduct().getProductId()));
                shopCartList.add(shopCart);
            }
        }
        return JSON.toJSONString(shopCartList);
    }
    /**
     * 删除购物车
     * @return
     */
    @GetMapping(value = "/delShop")
    @ResponseBody
    public String delShop(@RequestParam("skuId")Long skuId,HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        User u=null;
        if(useraccount!=null){
            u=useraccount.getUser();
        }
        if(u==null){
            //cookie
            Cookie[] c=request.getCookies();//获取cookie的所有数据
            //Cookie[] c=null;

            //遍历现有cookie数据
            if(c!=null){
                for (Cookie cookie : c) {
                    if(cookie.getName().equals("JSESSIONID")){
                        continue;
                    }
                    //解码
                    String strdecode= null;
                    try {
                        strdecode = URLDecoder.decode(cookie.getValue(),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Gson gson=new Gson();
                    ShopCart scart=new ShopCart();
                    scart=gson.fromJson(strdecode,scart.getClass());
                    //要添加的商品sku和cookie中已经有的商品sku是否一样
                    if(scart.getSku().getSkuId()==skuId){
                        //进来证明cookie中有这条skuid的商品，修改该skuid商品的数量
                        cookie.setMaxAge(0);//设置存活时间为0，也就是删除
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        return JSON.toJSONString(1);//方法结束cookie修改数量
                    }
                }
            }
        }else{
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
        }

        return JSON.toJSONString(1);
    }
    /**
     * 加减购物车
     * @return
     */
    @GetMapping(value = "/numPlusOrReduce")
    @ResponseBody
    public String numPlusOrReduce(@RequestParam("skuId")Long skuId, @RequestParam("num")int num,HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        User u=null;
        if(useraccount!=null){
            u=useraccount.getUser();
        }
        if(u==null){
            Cookie[] c=request.getCookies();//获取cookie的所有数据
            //Cookie[] c=null;
            //遍历现有cookie数据
            if(c!=null){
                for (Cookie cookie : c) {
                    if(cookie.getName().equals("JSESSIONID")){
                        continue;
                    }
                    //解码
                    String strdecode= null;
                    try {
                        strdecode = URLDecoder.decode(cookie.getValue(),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    cookie.setMaxAge(0);
                    Gson gson=new Gson();
                    ShopCart scart=new ShopCart();
                    scart=gson.fromJson(strdecode,scart.getClass());
                    //要添加的商品sku和cookie中已经有的商品sku是否一样
                    if(scart.getSku().getSkuId()==skuId){
                        //进来证明cookie中有这条skuid的商品，修改该skuid商品的数量
                        scart.setNum(num);
                        //编码
                        String _shopCartEncode= null;
                        try {
                            _shopCartEncode = URLEncoder.encode(JSON.toJSONString(scart), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Cookie ccc=new Cookie(cookie.getName(),_shopCartEncode);
                        ccc.setPath("/");
                        response.addCookie(ccc);
                        return JSON.toJSONString("修改数量成功");//方法结束cookie修改数量
                    }
                }
            }
        }else{
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
        }

        return "";
    }
    /**
     * 向订单模块传输所需数据
     * @return
     */
    @GetMapping(value = "/transferToOrder")
    @ResponseBody
    public String transferToOrder(@RequestParam(value = "productorders",required = false)String productorders){
        List<Long> productOrders=JSON.parseArray(productorders,Long.class);
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        User u=null;
        if(useraccount==null){
            return JSON.toJSONString(0);
        }
        u=useraccount.getUser();
        String dateKey=new Date().toString().replace(":","").replace(" ","");

        String keymapmap=u.getUserId().toString()+dateKey;
        //查出redis的现有数据
       Map<String,String> pp= jedisHashs.hgetAll(u.getUserId().toString());
        //保存要提交订单的购物车上牌信息
        Map<String,String> mmm=new HashMap<String,String>();
        for (Long ll:productOrders) {
            if(ll!=null){
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
                    if(kk.getSkuId()==ll){
                        mmm.put(key,pp.get(key));
                        it.remove();
                    }
                }
            }
        }

        String orderKey=u.getUserId().toString()+"order";
        //jedisHashs.hdel(u.getUserId().toString());
        jedisHashs.hmset(orderKey,mmm);//保存到redis
        jedisHashs.expire(orderKey,180);
       return JSON.toJSONString(1);
    }
}
