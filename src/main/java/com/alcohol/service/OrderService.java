package com.alcohol.service;

import com.alcohol.dto.OrderExecution;
import com.alcohol.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    //查看订单
    List<Order> order(Map<String,Object> map);
    //查看订单详情
    Order cha(@Param("order_id") int order_id);
    //修改退款状态
    int status(int order_id);



    /**
     * 进行下订单
     * @param order
     * @return
     */
    OrderExecution updateOrder(Order order);

    /**
     * 获取最后一个订单
     * @return
     */
    Order getLastOrderInfo(Long userId);
    //查询销售额
    List<Order> yearmoney(int year);
}
