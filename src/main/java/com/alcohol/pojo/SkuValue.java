package com.alcohol.pojo;

import lombok.Data;

/**
 * 胡博
 * Sku属性值
 */
@Data
public class SkuValue {
    private Long skuvalueId;//id
    private String skuvalueValue;//属性值
    private Long skunameId;//属性id
    private Long weight;//权重

    private skuName skuName;//SkuName对象
}
