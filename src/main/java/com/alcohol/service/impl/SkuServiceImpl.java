package com.alcohol.service.impl;

import com.alcohol.mapper.SkuMapper;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Sku;
import com.alcohol.service.SkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuMapper skuMapper;
    @Override
    public Sku getById(Long id) {
        return skuMapper.getById(id);
    }

    @Override
    public List<Sku> listById(List<Commodity> list) {
        return skuMapper.listById(list);
    }

    @Override
    public int updateInfo(Long skuId, Integer number, Integer status) {
        return skuMapper.updateInfo(skuId,number,status);
    }

    @Override
    public void receiveQueue(List<Commodity> list) {
        for (Commodity commodity: list  ) {
            if(commodity.getOrderstatusId() == 1){ //待付款
                skuMapper.updateInfo(commodity.getSkuId(),commodity.getNumber(),0);
            } else if(commodity.getOrderstatusId() == 7){
                skuMapper.updateInfo(commodity.getSkuId(),commodity.getNumber(),3);
            }
            System.out.println(list.size());
        }
    }
}
