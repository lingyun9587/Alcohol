package com.alcohol.service;

import com.alcohol.pojo.Product;

public interface ProductService {

    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid);
}
