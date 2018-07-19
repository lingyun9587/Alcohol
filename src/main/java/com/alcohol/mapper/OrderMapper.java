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
     * 查询全部订单过期的订单
     * @return
     */
     List<Order> listSellDaily();
    /**
     * 定时查看订单是否过期
     * @return
     */
    int orderSelldaily();

    //查询订单后台韩庆林
    List<Order>  order(Map<String,Object> map);
    //查看订单详情
    Order cha(Long orderId);
    //修改退款状态
    int status(Long order_id);
    //修改未发货的状态
    int fa(Long orderid);


    /**
     * 获取最后一个订单
     * @return
     */
    Order getLastOrderInfo(Long userId);
    //查询销售额
    List<Order> yearmoney(int year);
}
