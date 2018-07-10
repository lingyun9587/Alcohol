package com.alcohol.service;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 根据编号获得一条数据
     * @param productid
     * @return
     */
    public Product getProductbyId(Integer productid);

    /**
     * 查询首页的商品
     * @return
     */
    public List<Product> getProductByCategory(Map<String,Object> map);
    /**
     * 获取多个selTypeVlueId
     * @return
     */
    public List<Product> selTypeVlue();
    /**
     * 获取商品数据
     * @return
     */
    public List<Product> selAllDESC(Product product,int pageNum, int pageSize);

    /**
     * 模糊查
     * @param product
     * @return
     */
    public List<Product> selAll(Product product,int pageNum, int pageSize);


    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku
     * @return Sku
     */
    public Sku selectProductBySkuIdAndProductId(Long productId, Long skuId);
}
