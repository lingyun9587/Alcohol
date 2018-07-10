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
}
