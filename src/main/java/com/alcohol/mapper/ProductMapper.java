package com.alcohol.mapper;

import com.alcohol.pojo.Product;

import java.util.List;

/**
 * 商品接口
 */
public interface ProductMapper {

    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid);


    /**
     * 根据搜索商品名搜索商品
     * @return
     */
    public List<Product>  getProduct();
}
