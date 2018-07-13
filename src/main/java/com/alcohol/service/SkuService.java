package com.alcohol.service;

import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Sku;

import java.util.List;

public interface SkuService {

    /**
     * 根据sku查询信息
     * @param id
     * @return
     */
    Sku getById(Long id);

    /**
     * 根据集合查询信息
     * @param list
     * @return
     */
    List<Sku> listById(List<Commodity> list);
}
