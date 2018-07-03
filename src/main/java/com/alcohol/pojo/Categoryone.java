package com.alcohol.pojo;

import lombok.Data;

import java.util.List;

/***
 * 一级分类，张鹏
 */
@Data
public class Categoryone {

    //图片
    private List<Image> imageList;
    //sku属性
    private List<skuName> skunameList;

    //sku配合
    private List<Sku> skuList;
    private Long categoryoneId;
    private String categoryontName;//类别名称
    private Long weight;//权重
}
