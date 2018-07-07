package com.alcohol.service;

import com.alcohol.pojo.Product;

import java.util.List;

public interface ProductService {

    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid);
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
    public List<Product> selAll(Product product);
}
