package com.alcohol.service.impl;

import com.alcohol.mapper.ProductMapper;
import com.alcohol.pojo.Product;
import com.alcohol.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Product> selTypeVlue() {
        return productMapper.selTypeVlue();
    }

    @Override
    public List<Product> selAllDESC(Product product,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return productMapper.selAllDESC(product);
    }

    @Override
    public List<Product> selAll(Product product) {
        return productMapper.selAll(product);
    }

    @Override
    public List<Product> getProductByCategory(Map<String, Object> map) {
        return productMapper.getProductByCategory(map);
    }
}
