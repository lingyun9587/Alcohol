package com.alcohol.mapper;

import com.alcohol.pojo.Image;

import java.util.List;

/**
 * 王磊
 */
public interface ImageMapper {
    /**
     * 轮播图
     * @return
     */
    List<Image> list();

    /**
     * 根据商品id查询图片
     * @return
     */
    List<Image> selImageByProductId(Long productId);

    /**
     * 商品图片/评论图片
     * @param image
     * @return
     */
    int addImage(Image image);
}
