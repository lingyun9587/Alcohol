package com.alcohol.pojo;

import lombok.Data;

/**
 * 胡博
 * 分类检索属性值
 */
@Data
public class Typevalue {
    private Long typeValueId;
    private String typeValueName;//属性值
    private Long typeNameId;//属性id
    private Long weight;//权重
    private Integer isdel;//是否删除
}
