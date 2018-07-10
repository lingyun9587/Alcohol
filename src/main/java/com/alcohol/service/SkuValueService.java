package com.alcohol.service;

import com.alcohol.pojo.Sku;
import com.alcohol.pojo.SkuValue;

/**
 * skuvalue表
 */
public interface SkuValueService {

    /**
     * 根据id获取sku的名称
     * @param id
     * @return
     */
    SkuValue getSkuById(Integer id);

    Sku getSkuBiProductId(String value,Integer id);
}
