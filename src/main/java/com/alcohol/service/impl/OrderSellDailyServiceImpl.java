package com.alcohol.service.impl;

import com.alcohol.mapper.CommodityMapper;
import com.alcohol.mapper.OrderMapper;
import com.alcohol.mapper.SkuMapper;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Order;
import com.alcohol.service.OrderSellDailyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderSellDailyServiceImpl  implements OrderSellDailyService {

    @Resource
    private SkuMapper skuMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CommodityMapper commodityMapper;
    @Override
    public void dailyOrderSupermarketCheck() {
        List<Order> orders = orderMapper.listSellDaily();
        orderMapper.orderSelldaily();
        if(orders != null && orders.size()>0){
            List<Commodity> commodities = commodityMapper.listCommoditySail(orders);
            for (Commodity commodity:commodities) {
                skuMapper.updateInfo(commodity.getSkuId(),commodity.getNumber(),1);
            }
            commodityMapper.deleteSailDaily(orders);
        }
        System.out.println("定时查看订单");
    }
}
