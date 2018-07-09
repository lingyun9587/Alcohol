package com.alcohol.service;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid);

    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku
     * @return Sku
     */
    public Sku selectProductBySkuIdAndProductId(Long productId, Long skuId);
}
