package com.alcohol.pojo;

import lombok.Data;

/***
 * 三级分类张鹏
 */
@Data
public class Categorythree {
    private Long categorythreeId;
    private String categorythreeName;//类别名称
    private  Long parentId;//耳机分类id
    private Long weight;//权重
}
