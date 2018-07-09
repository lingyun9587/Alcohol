package com.alcohol.mapper;

import com.alcohol.pojo.Orderstatus;
import com.alcohol.vo.OrderstatusVo;

import java.util.List;

/**
 * 订单状态
 */
public interface OrderstatusMapper {

    /**
     * 获取所有订单状态信息
     * @return
     */
    List<Orderstatus > listAllInfo();

    /**
     * 根据用户id获取所有状态的订单数
     * @param userId
     * @return
     */
    List<OrderstatusVo> listByUserId(Long userId);

}
