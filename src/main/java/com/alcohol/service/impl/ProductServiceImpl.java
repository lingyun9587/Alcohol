package com.alcohol.service.impl;

import com.alcohol.mapper.ProductMapper;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import com.alcohol.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Sku getProductBySkuIdAndProductId(Long product, int skuId) {
        Sku sku=null;
        for (Sku s:productMapper.selectProductBySkuIdAndProductId(product,skuId)) {
            sku=s;
        }
        return sku;
    }
}
