package com.alcohol.mapper;

import com.alcohol.controller.CommonController;
import com.alcohol.pojo.Commodity;
import com.alcohol.vo.OrderstatusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品详情 陈赓
 */
public interface CommodityMapper {


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

    /**
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
     List<OrderstatusVo> listVoByUserId(Integer id);

    /**
     * 查询所有用户的订单信息
     * @param id
     * @return
     */
     List<Commodity> listCommodityInfo(@Param("id")Integer id, @Param("status")Integer status);
}
