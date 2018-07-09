package com.alcohol.service.impl;

import com.alcohol.mapper.SkuValueMapper;
import com.alcohol.pojo.Sku;
import com.alcohol.pojo.SkuValue;
import com.alcohol.service.SkuValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SkuValueServiceImpl  implements SkuValueService {


    @Resource
    private SkuValueMapper skuValueMapper;
    @Override
   public SkuValue getSkuById(Integer id){
        return skuValueMapper.getSkuById(id);
    }

    @Override
    public Sku getSkuBiProductId(String value, Integer id){
        return skuValueMapper.getSkuBiProductId(value,id);
    }
}
