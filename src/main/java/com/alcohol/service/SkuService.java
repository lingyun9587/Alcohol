package com.alcohol.service;

import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 根据sku编号修改数量
     * @param skuId
     * @param number
     * @param  status 0锁定 1 解锁
     * @return
     */
    int updateInfo( Long skuId, Integer number,  Integer status);

    /**
     * 执行消息队列消息
     * @param list
     */
    void receiveQueue(List<Commodity> list);
}
