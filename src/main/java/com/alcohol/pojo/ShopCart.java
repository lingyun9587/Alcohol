package com.alcohol.pojo;

import lombok.Data;

import java.util.List;

/**
 * 赵俊峰
 * 购物车
 */
@Data
public class ShopCart {
    private Sku sku;//sku商品对象0
    private Image image;//图片对象
    private int num;//用户选择的数量
}
