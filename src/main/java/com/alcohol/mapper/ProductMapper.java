package com.alcohol.mapper;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品
 * 赵俊峰
 */
public interface ProductMapper {
    //根据skuid和商品id查询该商品信息
    List<Sku> selectProductBySkuIdAndProductId(@Param("productId")Long product, @Param("skuId")int skuId);
}
