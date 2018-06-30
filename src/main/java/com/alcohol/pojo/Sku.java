package com.alcohol.pojo;

import lombok.Data;

/**
 * 胡博
 * Sku 表组合表
 */
@Data
public class Sku {
    private Long skuId;//主键id
    private Long productId;//商品id
    private String skuvalueId;//sku对应值
    private Long stock;//库存
    private double originalPrice;//原价
    private double presentPrice;//现价
}
