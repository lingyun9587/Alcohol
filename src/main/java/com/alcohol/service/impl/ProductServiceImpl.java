package com.alcohol.service.impl;

import com.alcohol.mapper.ProductMapper;
import com.alcohol.pojo.Product;
import com.alcohol.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;
    @Override
    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid){
        return productMapper.getProductbyId(productid);
    }
}
