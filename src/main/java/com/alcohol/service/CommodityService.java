package com.alcohol.service;

import com.alcohol.dto.CommodityExecution;
import com.alcohol.pojo.Commodity;
import com.alcohol.vo.OrderstatusVo;

import java.util.List;

/**
 * 陈赓
 */
public interface CommodityService {

    /**
     * 查询用户所有订单状态数量
     * @param id
     * @return
     */
    List<OrderstatusVo> listVoByUserId(Long id);

    /**
     * 查询所有用户的订单信息
     * @param id
     * @return
     */
    List<Commodity> listCommodityInfo(Long id,Integer status);

    /**
     * 删除商品详细信息
     * @param orderId
     * @param commodityId
     * @return
     */
    CommodityExecution removeId(Long orderId,Long commodityId);
}
