package com.alcohol.controller;

import com.alcohol.config.redis.RedisConfiguration;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import com.alcohol.pojo.User;
import com.alcohol.service.ProductService;
import org.apache.http.HttpRequest;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 */
@Controller
public class ShopCartController {
    @Resource
    private ProductService productService;
    /**
     * 添加购物车
     * @return
     */
    @GetMapping(value = "/addShopCart")
    @ResponseBody
    public String addShopCart(/*@RequestParam(value = "productId",required = false)int productId,
                              @RequestParam(value = "skuId",required = false)int skuId,
                              @RequestParam(value = "num",required = false)int num,*/
                              HttpServletRequest request, HttpServletResponse response, HttpSession session){
       Long productId=2L;
       Integer skuId=4;
       Integer num=3;
        User u=new User();
        u.setUserId((long) 3);
        u.setNickName("zz");
         request.getSession().setAttribute("user",u);//向session中保存数据
         User user=(User) session.getAttribute("user");//获取session中的数据


        StringBuffer sb=new StringBuffer();
        sb.append(productId);
        sb.append("#");//使用逗号分开，以便取值
        sb.append(skuId);
        sb.append("#");
        String str1= sb.append(num).toString();
        //按照商品id和skuid查询出对应的sku和商品信息
        Sku sku= productService.getProductBySkuIdAndProductId(productId,skuId);
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
            return "";//方法结束cookie添加
         }else{//证明已经登录，操作redis
             RedisConfiguration rc=new RedisConfiguration();
             JedisPoolConfig config= rc.jedisPoolConfig();
             JedisPool pool=new JedisPool(config,"192.168.159.128",6379);
             Jedis jedis = null;

             try  {
                 jedis = pool.getResource();
                 jedis.auth("123456");
                 jedis.set("name", "lisi");
                 String name = jedis.get("name");
                 System.out.println(name);
             }catch(Exception ex){
                 ex.printStackTrace();
             }finally{
                 if(jedis != null){
                     //关闭连接
                     jedis.close();
                 }
             }
             return "";//方法结束，redis添加，修改
         }

    }
}
