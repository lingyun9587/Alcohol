package com.alcohol.mapper;

import com.alcohol.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单 陈赓
 */
public interface OrderMapper {


    /**
     * 添加订单信息
     * @param order
     * @return
     */
    int insertInfo(Order order);

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    int updateOrderState(Order order);

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    int updateOrderInfo(Order order);

    /**
     * 删除订单信息
     * @param orderId
     * @return
     */
    int removeOrderInfo(Long orderId);
    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
     List<Order>  listByUserId(Integer userId);
    /**
     * 根据订单号查询用户商品
     * @param orderId
     * @return
     */
     Order getById(@Param("orderId") Long orderId);



    /**
     * 查询订单  韩庆林
     * @param
     * @return
     */
    List<Order>  order(Map<String,Object> map);

    /**
     * 查看订单详情  韩庆林
     * @param
     * @return
     */
    Order cha(@Param("order_id") int order_id);

    /**
     * 查修改退款状态  韩庆林
     * @param
     * @return
     */
    int status(int order_id);
}
