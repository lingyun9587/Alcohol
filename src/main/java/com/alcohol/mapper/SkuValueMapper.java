package com.alcohol.mapper;

import com.alcohol.pojo.SkuValue;

/**
 * skuvalue表
 */
public interface SkuValueMapper {

    /**
     * 根据id获取sku的名称
     * @param id
     * @return
     */
    SkuValue getSkuById(Integer id);
}
