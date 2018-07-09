package com.alcohol.mapper;

import com.alcohol.pojo.Order;
import com.alcohol.pojo.OrderShop;

import java.util.List;


/**
 * 订单商铺
 */
public interface OrderShopMapper {

    /**
     * 添加订单中的商品信息
     * @param orderShop
     * @return
     */
    int insertInfo(OrderShop orderShop);

    /**
     * 根据商铺进行删除
     * @param ordershopId
     * @return
     */
    int removeById(Integer ordershopId);

}
