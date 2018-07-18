package com.alcohol.service;

import com.alcohol.pojo.Image;

import java.util.List;

/**
 * 王磊
 */
public interface ImageService {
    /**
     * 轮播图
     * @return
     */
    List<Image> list();

    /**
     * 根据商品id查询图片
     * @return
     */
    Image selImageByProductId(Long productId);

    /**
     * 根据商品id查询商品图片
     * @param productId
     * @return
     */
    List<Image> selProductId(Long productId);

    /**
     * 商品图片/评论图片
     * @param image
     * @return
     */
    int addImage(Image image);
}
