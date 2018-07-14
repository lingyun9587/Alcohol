package com.alcohol.mapper;

import com.alcohol.controller.CommonController;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Order;
import com.alcohol.vo.OrderstatusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品详情 陈赓
 */
public interface CommodityMapper {


    List<OrderstatusVo> listVoByUserId(Long id);
    /**
     * 修改订单商品信息
     * @param commodity
     * @return
     */
    int updateInfo(Commodity commodity);
    /**
     * 根据用户订单id查询订单中的商铺信息
     * @param userId
     * @return
     */
     List<Commodity>  listByUserId(Integer userId);

    /**
     * 添加商品详情
     * @param commodity
     * @return
     */
     int insertInfo(Commodity commodity );

    /**     List<OrderstatusVo> listVoByUserId(Integer id);

     * 删除订单信息
     * @param commodityId
     * @return
     */
     int removeById(Long commodityId);

    /**
     * 配合上面所有的方法进行使用
     * @param orderId
     * @return
     */
     int selectByOrderId(Long orderId);
    /**
     * 查询用户所有订单状态数量
     * @param id
     * @return
     */

    /**
     * 查询所有用户的订单信息
     * @param id
     * @return
     */
     List<Commodity> listCommodityInfo(@Param("id")Long id, @Param("status")Integer status);

    /**
     * 获取所有 超时订单信息
     * @return
     */
     List<Commodity> listCommoditySail(List<Order> list);
    /**
     * 批量删除订单超时信息
     * @param list
     */
    void deleteSailDaily(List<Order> list);


    /**
     * 根据订单编号修改状态
     * @param
     * @return
     */
    int updateCommodityStatusByOrderId(@Param("orderId") Long orderId,@Param("status") Integer status);
}
