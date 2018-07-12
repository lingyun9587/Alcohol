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
     *  查看订单 韩庆林
     * @param map
     * @return
     */
    List<Order> order(Map<String,Object> map);


    /**
     * 查看订单详情 韩庆林
     * @param order_id
     * @return
     */
    Order cha(@Param("order_id") int order_id);


    /**
     * 修改退款状态 韩庆林
     * @param order_id
     * @return
     */
    int status(int order_id);

}
