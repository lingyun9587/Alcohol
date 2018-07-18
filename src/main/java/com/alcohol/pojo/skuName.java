package com.alcohol.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 胡博
 * sku属性
 */
@Data
public class skuName {

    //属性值
    private List<SkuValue> skuValueList=new ArrayList<SkuValue>();
    private Long skunameId;//id
    private String skunameeValue;//属性值
    private Long productId;//商品id
    private Long weight;//权重
}
