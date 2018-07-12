package com.alcohol.pojo;

import com.alcohol.util.LongJsonDeserializer;
import com.alcohol.util.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/***
 * 订单商品详情张鹏
 */
@Data
public class Commodity implements Serializable {

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long commodityId;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orderId; //订单号
    private Long orderShopId;//订单商铺id
    private Long skuId; //商品编号
    private Integer number; //数量
    private Long orderstatusId; //订单状态id

    private Order order; //订单对象
    private Sku sku; //Sku对象
    private Orderstatus orderstatus; //订单状态


}
