package com.alcohol.service.impl;

import com.alcohol.dto.OrderExecution;
import com.alcohol.enums.OrderEnum;
import com.alcohol.exceptions.OrderOperationException;
import com.alcohol.mapper.CommodityMapper;
import com.alcohol.mapper.OrderMapper;
import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Order;
import com.alcohol.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

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
        return orderExecution;
    }

    @Override
    public Order getById(Long id)throws OrderOperationException {

            Order order = orderMapper.getById(id);

        return order;
    }
}
