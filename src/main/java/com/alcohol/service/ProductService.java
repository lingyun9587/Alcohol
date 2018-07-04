package com.alcohol.service;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    //根据skuid和商品id查询该商品信息
    Sku getProductBySkuIdAndProductId(Long product, int skuId);
}
