package com.alcohol.controller;

import com.alcohol.cache.JedisUtil;
import com.alcohol.config.alipay.AlipayConfig;
import com.alcohol.service.jms.ProducerCc;
import com.alcohol.pojo.*;
import com.alcohol.service.OrderService;
import com.alcohol.service.ProductService;
import com.alcohol.service.SkuService;
import com.alcohol.service.UserAccountService;
import com.alcohol.util.IDUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
public class AlipayController {
    @Resource
    private UserAccountService userAccountService;
    @Resource
    private ProductService productService;
    @Resource
    private SkuService skuService;
    @Resource
    private JedisUtil.Hash jedisHashs;

    @Resource
    private ProducerCc producerCc;
    @RequestMapping(value = "/alipay/index.html")
    private String index(){
        return "/alipay/index";
    }

    @Resource
    private OrderService orderService;

    /**
     * 快捷支付调用支付宝支付接口
     * @param model，id，payables，
     * @throws IOException ，AlipayApiException
     * @return Object
     */
    @ResponseBody
    @RequestMapping(value = "/alipaySum")
    public Object alipayIumpSum(Model model, HttpServletRequest request)
            throws Exception {
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("UTF-8"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("UTF-8"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("UTF-8"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("UTF-8"),"UTF-8");
        //状态
        String skuId =new String(request.getParameter("skuId").getBytes("UTF-8"),"UTF-8");
        String number1 =new String(request.getParameter("number").getBytes("UTF-8"),"UTF-8");
        String status =new String(request.getParameter("status").getBytes("UTF-8"),"UTF-8");
        String addr =new String(request.getParameter("addr").getBytes("UTF-8"),"UTF-8");
        String desc =new String(request.getParameter("desc").getBytes("UTF-8"),"UTF-8");
        if(status.equals("0")){ //订单
            //处理订单付款成功方法
            Long orderId =Long.valueOf(out_trade_no);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setDesc(desc);
            order.setStatus(1); //待发货
            orderService.updateOrderStatus(order);
        }else if(status.equals("1")){  //立即购买

            Long orderId = IDUtil.SnowflakeIdWorker();

            out_trade_no=orderId.toString();
            //创建订单对象
            Order order1 = new Order();
            order1.setOrderId(orderId);  //设置订单id
            order1.setCreateTime(new Date());  //设置订单创建时间
            order1.setUserId(useraccount.getUserId());  //设值用户
            order1.setStatus(1);   //设置订单状态
            order1.setDesc(desc);
            order1.setAddressId(Long.valueOf(addr));  //设置地址id  测试
            List<Commodity> commodities = new ArrayList<>();  //商铺下的商品
            //添加订单商品信息
            long commodityID = IDUtil.SnowflakeIdWorker();
            Commodity commodity = new Commodity();
            commodity.setCommodityId(commodityID);  //详情id
           // commodity.setOrderShopId(sku.getProduct().getUserId());  //商品id
            commodity.setOrderId(orderId);  //订单id
            commodity.setNumber(Integer.parseInt(number1));   //数量
            commodity.setSkuId(Long.valueOf(skuId));  //skuId
            commodity.setOrderstatusId(1L);  //1待付款
            commodities.add(commodity);  //添加到订单信息
            order1.setCommodities(commodities);
            order1.setGoodsCount(Integer.parseInt(number1));  //设置总数量
            order1.setMoney(Double.valueOf(total_amount));    //设置总金额
            orderService.insertInfo(order1);//发送消息队列进行修改库存
        }else if(status.equals("2")){ //购物车


            Long orderId = IDUtil.SnowflakeIdWorker();
            out_trade_no=orderId.toString();
            //创建订单对象
            Order order1 = new Order();
            order1.setOrderId(orderId);  //设置订单id
            order1.setCreateTime(new Date());  //设置订单创建时间
            order1.setUserId(useraccount.getUserId());  //设值用户
            order1.setDesc(desc);
            order1.setStatus(1);   //设置订单状态
            order1.setAddressId(Long.valueOf(addr));  //设置地址id  测试
            Map<String,String> mapshopping= jedisHashs.hgetAll(useraccount.getUserId()+"order");
            int number = 0;  //总数数量
            double money = 0;  //总金额
            List<Commodity> commodities = new ArrayList<>();  //商铺下的商品

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
              //  skuService.updateInfo(sku.getSkuId(),number,3); //修改库存

            }


            order1.setCommodities(commodities);  //设置订单的商品详情集合
            order1.setGoodsCount(number);  //设置总数量
            order1.setMoney(money);    //设置总金额
            orderService.insertInfo(order1);//发送消息队列进行修改库存
            //开始////////////////
            //开始清空redis中所购买的商品/////////////
            //查出redis的现有数据
            Map<String,String> allRedisByUserID= jedisHashs.hgetAll(useraccount.getUserId().toString());
            Iterator<String> it=allRedisByUserID.keySet().iterator();
            //结束清空redis中所购买的商品//////////////
            //结束////////////////////
            while(it.hasNext()){
                String key=it.next();
                String obj=allRedisByUserID.get(key);
                Gson gs=new Gson();
                Map<String,Object> mk=new HashMap<String,Object>();
                mk=gs.fromJson(obj,mk.getClass());
                Sku kk=new Sku();
                kk=gs.fromJson(JSON.toJSONString(mk.get("sku")),kk.getClass());
                for (String str: mapshopping.keySet()) {
                    String so =  mapshopping.get(str);
                    Gson gson = new Gson();
                    Map<String,Object> map111 = new HashMap<>();
                    map111 = gson.fromJson(so,map111.getClass());
                    Sku sku = new Sku(); //获取sku对象
                    sku = gson.fromJson(JSON.toJSONString(map111.get("sku")),sku.getClass());
                    if(kk.getSkuId()==sku.getSkuId()){
                        it.remove();
                    }else{
                        continue;
                    }
                }
            }

            if(allRedisByUserID.size()!=0){
                jedisHashs.hmset(useraccount.getUserId().toString(),allRedisByUserID);//保存到redis
            }else{
                jedisHashs.hdel(useraccount.getUserId().toString());
            }

            //结束/////////////////////////////


        }
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        return result;
    }

}
