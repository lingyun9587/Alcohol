package com.alcohol.service;

import com.alcohol.pojo.Sku;
import com.alcohol.pojo.SkuValue;
import com.alcohol.pojo.skuName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * skuvalue表
 */
public interface SkuValueService {

    /**
     * 查询skuvalueid
     * @param productId
     * @param name
     * @return
     */
    public SkuValue getSkuValueIdByname(@Param("productId")Long productId,
                                        @Param("name")String name);


    /**
     * 新增sku属性
     * @param name
     * @return
     */
    public int addSkuName(skuName name);

    /**
     * 新增sku属性值
     * @param value
     * @return
     */
    public int addSkuValue(SkuValue value);

    /**
     * 新增sku组合
     * @param sku
     * @return
     */
    public int addSku(Sku sku);

    /**
     * 根据id获取sku的名称
     * @param id
     * @return
     */
    SkuValue getSkuById(Integer id);

    Sku getSkuBiProductId(String value,Integer id);

    /***
     * 修改库存
     * @param skuId
     * @param number
     * @return
     */
    int upgStock(@Param("skuId")Integer skuId, @Param("number")Integer number);

    /***
     * 获取商品sku规格
     * @param ProductId
     * @return
     */
    List<Sku> getSkuByProduct(Integer ProductId);
}
