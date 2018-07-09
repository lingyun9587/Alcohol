package com.alcohol.pojo;

import com.alcohol.util.LongJsonDeserializer;
import com.alcohol.util.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 订单表
 */
@Data
public class Order {

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orderId;
    private  Long addressId;  //地址id
    private Date createTime;  //创建时间
    private Date paymentTime;  //支付时间
    private  String batch;  //交易流水号
    private  Long userId;  //用户id
    private  Integer goodsCount;  //订单中的商品数量
    private  Integer status;  //状态
    private  Double money; //金额

    private Orderstatus orderstatus; //订单状态
    //private Set<OrderShop> orderShops;  //订单商铺id
    private List<Commodity> commodities; //订单信息
}
