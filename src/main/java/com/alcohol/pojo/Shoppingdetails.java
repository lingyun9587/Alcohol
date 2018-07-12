package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 胡博
 * 购物详情
 */
@Data
public class Shoppingdetails {
    private Long shoppingDetailsId;
    private Long shoppingId;//用户编号
    private Long skuId;//sku编号
    private Long number;//数量
    private Date createTime;//加入时间

    //sku表  韩庆林
    private List<Sku> sku;
}
