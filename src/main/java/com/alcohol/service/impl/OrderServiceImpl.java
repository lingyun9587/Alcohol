package com.alcohol.service.impl;

import com.alcohol.dto.OrderExecution;
import com.alcohol.enums.OrderEnum;
import com.alcohol.exceptions.OrderOperationException;
import com.alcohol.service.jms.ProducerCc;
import com.alcohol.mapper.CommodityMapper;
import com.alcohol.mapper.OrderMapper;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Order;
import com.alcohol.service.OrderService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private ProducerCc producerCc;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CommodityMapper commodityMapper;
    @Override
    public OrderExecution insertInfo(Order order)throws OrderOperationException {
        OrderExecution orderExecution =null;
        try{
        int result = orderMapper.insertInfo(order);
        if(result>0){
            int result1 =0;
            for (Commodity commodity:order.getCommodities()) {
                result1 = commodityMapper.insertInfo(commodity);
                if(result1 >0 ){
                    continue;
                }else{
                    break;
                }
            }
            if(result1 > 0){
                orderExecution =  new OrderExecution(OrderEnum.SUCCESS);
                //成功之后添加商铺
            }else{
                orderExecution = new OrderExecution(OrderEnum.NOFAIL);
            }
        }else{
            return new OrderExecution(OrderEnum.NOFAIL);
        }
        }catch (OrderOperationException e){
            throw  new OrderOperationException(e.toString());
        }
       /* if(orderExecution.getState() == 0){ //执行消息队列
            producerCc.sendMessage(JSON.toJSONString(order.getCommodities()));
        }*/
        return orderExecution;
    }

    @Override
    public Order getById(Long id)throws OrderOperationException {

            Order order = orderMapper.getById(id);

        return order;
    }

    @Override
    public OrderExecution updateOrderStatus(Order order)throws OrderOperationException {
        OrderExecution orderExecution =null;
        try{
            int result = orderMapper.updateOrderState(order);
            if(result>0){
                commodityMapper.updateCommodityStatusByOrderId(order.getOrderId(),order.getStatus());
                orderExecution = new OrderExecution(OrderEnum.SUCCESS);
            }else{
                orderExecution = new OrderExecution(OrderEnum.NOTFAIL);
            }
        }catch (OrderOperationException e){
            throw new OrderOperationException(e.toString());
        }
        return orderExecution;
    }

    @Override
    public List<Order> order(Map<String, Object> map) {
        return null;
    }

    @Override
    public Order cha(int order_id) {
        return null;
    }

    @Override
    public int status(int order_id) {
        return 0;
    }

    @Override
    public OrderExecution updateOrder(Order order) {
        return null;
    }

    @Override
    public Order getLastOrderInfo(Long userId) {
        return orderMapper.getLastOrderInfo(userId);
    }

    @Override
    public List<Order> yearmoney(int year) {
        return orderMapper.yearmoney(year);
    }
}
