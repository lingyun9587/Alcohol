package com.alcohol.dto;

import com.alcohol.enums.CommodityEnum;
import com.alcohol.enums.OrderEnum;
import com.alcohol.pojo.Commodity;

import java.util.List;

public class CommodityExecution {
    private  int state;

    private  String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private Commodity commodity;

    private List<Commodity> commodities;

    //失败的构造器
    public  CommodityExecution(CommodityEnum orderEnum){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
    }
    public  CommodityExecution(CommodityEnum orderEnum, Commodity order){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
        this.commodity = order;
    }
    public  CommodityExecution(CommodityEnum orderEnum,List<Commodity> orders){
        this.state = orderEnum.getState();
        this.stateInfo=orderEnum.getStateInfo();
        this.commodities = orders;
    }
}
