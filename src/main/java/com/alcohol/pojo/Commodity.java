package com.alcohol.pojo;

import lombok.Data;

/***
 * 订单商品详情张鹏
 */
@Data
public class Commodity {
    private Long commodityId;
    private Long orderId; //订单号
    private Long orderShopId;//订单商铺id
    private Long skuId; //商品编号
    private Double number; //数量

}
