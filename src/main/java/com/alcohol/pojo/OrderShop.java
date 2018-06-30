package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class OrderShop {

    private Long  orderShopId;
    private  Long orderId;  //订单号
    private  Long userId;  //用户编号
    private  String wayBill;  //运单号
    private Date deliveryTime; //发货时间
}
