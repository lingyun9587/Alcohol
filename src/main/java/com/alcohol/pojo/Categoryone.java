package com.alcohol.pojo;

import lombok.Data;

import java.util.List;

/***
 * 一级分类，张鹏
 */
@Data
public class Categoryone {
    private Long categoryoneId;
    private String categoryontName;//类别名称
    private Long weight;//权重
    private Integer isdel;//是否删除
    private List<Categorytwo> categorytwoList;
}
