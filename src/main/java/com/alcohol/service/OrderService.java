package com.alcohol.service;

import com.alcohol.dto.OrderExecution;
import com.alcohol.pojo.Order;

/**
 * 订单控制层
 */
public interface OrderService {

    /**
     * 添加订单信息
     * @param order
     * @return
     */
    OrderExecution insertInfo(Order order);

    /**
     * 根据id获取订单信息
     * @param id
     * @return
     */
    Order getById(Long id);

    /**
     * 修该订单信息状态
     * @param order
     * @return
     */
    OrderExecution updateOrderStatus(Order order);

    /**
     * 进行下订单
     * @param order
     * @return
     */
    OrderExecution updateOrder(Order order);

}
