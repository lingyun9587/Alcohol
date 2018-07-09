package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Set;

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

    private Set<Commodity> commodities; //订单商品详情集合
}
