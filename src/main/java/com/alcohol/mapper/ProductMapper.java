package com.alcohol.mapper;

import com.alcohol.pojo.Product;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取多个selTypeVlueId
     * @return
     */
    public List<Product> selTypeVlue();

    /**
     * 获取商品数据
     * @return
     */
    public List<Product> selAllDESC(Product product);

    /**
     * 模糊查倒叙
     * @param product
     * @return
     */
    public List<Product> selAll(Product product);

    /**
     * 查询首页的商品 李清
     * @return
     */
    public List<Product> getProductByCategory(Map<String,Object> map);
}
