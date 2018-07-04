package com.alcohol.dto;

import com.alcohol.enums.OrderEnum;
import com.alcohol.pojo.Order;

import java.util.List;

public class OrderExecution {

    private  int state;

    private  String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private Order order;

    private List<Order> orders;

    //失败的构造器
    public  OrderExecution(OrderEnum orderEnum){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
    }
    public  OrderExecution(OrderEnum orderEnum,Order order){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
        this.order = order;
    }
    public  OrderExecution(OrderEnum orderEnum,List<Order> orders){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
        this.orders = orders;
    }
}
