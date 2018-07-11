package com.alcohol.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 胡博
 * 商品信息表
 */
@Data
public class Product {

    //图片
    private List<Image> imageList;
    //sku属性
    private List<skuName> skunameList;
    //sku配合
    private List<Sku> skuList;

    private String imagePath;      //图片路径
    private Double presentPrice;   //商品最低价
    private int countId;           //评价总记录数
    private int spare_one;         //浏览量
    private int judge;             //判断用那种方式排序 1销量  2销量倒叙  3评论  4评论倒叙 5价格  6价格倒叙

    private Long productId;//商品编号
    private String productName;//商品名称
    private String productDese;//描述
    private String productDetails;//商品详细描述
    private Date createTime;//上架时间
    private Date downTime;//下架时间
    private Date modiyTime;//修改时间
    private Long status;//上下架
    private Long sales;//销量
    private Long longegral;//赠送积分
    private Long categoryOne;//一级分类
    private Long categoryTow;//二级分类
    private Long categoryThree;//三级分类
    private String typevalueId;//属性值
    private Long weight;//权重
    private Long userId;
    private List<Typevalue> typeList=new ArrayList<Typevalue>();
    private Long panduan;//判断的值
}
