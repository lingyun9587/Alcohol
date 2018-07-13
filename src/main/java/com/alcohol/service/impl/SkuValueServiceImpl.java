package com.alcohol.service.impl;

import com.alcohol.mapper.SkuValueMapper;
import com.alcohol.pojo.Sku;
import com.alcohol.pojo.SkuValue;
import com.alcohol.pojo.skuName;
import com.alcohol.service.SkuValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SkuValueServiceImpl  implements SkuValueService {


    @Resource
    private SkuValueMapper skuValueMapper;

    @Override
    public SkuValue getSkuValueIdByname(Long productId, String name) {
        return skuValueMapper.getSkuValueIdByname(productId,name);
    }

    @Override
    public int addSkuName(skuName name) {
        return skuValueMapper.addSkuName(name);
    }

    @Override
    public int addSkuValue(SkuValue value) {
        return skuValueMapper.addSkuValue(value);
    }

    @Override
    public int addSku(Sku sku) {
        return skuValueMapper.addSku(sku);
    }

    @Override
   public SkuValue getSkuById(Integer id){
        return skuValueMapper.getSkuById(id);
    }

    @Override
    public Sku getSkuBiProductId(String value, Integer id){
        return skuValueMapper.getSkuBiProductId(value,id);
    }
}
