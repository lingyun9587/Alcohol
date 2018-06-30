package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 订单表
 */
@Data
public class Order {

    private Long orderId;
    private  Long addressId;  //地址id
    private Date createTime;  //创建时间
    private Date paymentTime;  //支付时间
    private  String batch;  //交易流水号
    private  Long userId;  //用户id
    private  Integer goodsCount;  //订单中的商品数量
    private  Integer status;  //状态
    private  Double money; //金额
}
