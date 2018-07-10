package com.alcohol.mapper;

import com.alcohol.pojo.Sku;
import com.alcohol.pojo.SkuValue;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 点击商品sku属性绑定值
     * @param id
     * @return
     */
    Sku getSkuBiProductId(@RequestParam("value") String value,@RequestParam("id") Integer id);
}