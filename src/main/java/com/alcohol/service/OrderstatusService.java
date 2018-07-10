package com.alcohol.service;

import com.alcohol.vo.OrderstatusVo;

import java.util.List;

public interface OrderstatusService {

    /**
     * 根据用户id获取所有状态的订单数
     * @param userId
     * @return
     */
    List<OrderstatusVo> listByUserId(Long userId);
}
