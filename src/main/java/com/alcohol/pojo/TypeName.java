package com.alcohol.pojo;

import lombok.Data;

/**
 * 胡博
 * 分类检索属性表
 */
@Data
public class TypeName {
    private Long typeNameId;
    private String typeNameName;//属性名
    private Long categoryId;//商品三级分类
}
