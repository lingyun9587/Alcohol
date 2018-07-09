package com.alcohol.service.impl;

import com.alcohol.mapper.ProductMapper;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
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
    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku
     * @return Sku
     */
    @Override
    public Sku selectProductBySkuIdAndProductId(Long productId, Long skuId) {
        Sku sku=null;
        for (Sku s:productMapper.selectProductBySkuIdAndProductId(null,skuId)) {
            sku=s;
        }
        return sku;
    }
}
