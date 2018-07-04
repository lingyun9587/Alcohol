package com.alcohol.pojo;

import lombok.Data;

/***
 * 二级分类张鹏
 */
@Data
public class Categorytwo {
    private Long categorytwoId;
    private String categorytwoName;//类别名称
    private  Long parentId;//一级分类id
    private Long weight;//权重
    private Integer isdel;//是否删除
}
