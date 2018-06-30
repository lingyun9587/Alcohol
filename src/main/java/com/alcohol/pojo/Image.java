package com.alcohol.pojo;

import lombok.Data;

/**
 * 图片表
 */
@Data
public class Image {

    private Long imageId;
    private String imagePath; //图片路径
    private Long productId; //所属id
    private String imageType; //图片类型
    private Long weight; //权重

}
