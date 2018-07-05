package com.alcohol.pojo;

import lombok.Data;

import java.util.List;

/***
 * 三级分类张鹏
 */
@Data
public class Categorythree {
    private Long categorythreeId;
    private String categorythreeName;//类别名称
    private  Long parentId;//耳机分类id
    private Long weight;//权重
    private Integer isdel;//是否删除
    private List<TypeName> typeNameList;//属性集合
}
