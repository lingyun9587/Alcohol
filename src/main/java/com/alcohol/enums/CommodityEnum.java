package com.alcohol.enums;

public enum CommodityEnum {
    NOFAIL(-1, "操作失败"),
    NOTFAIL(-5,"其他异常"),
    SUCCESS(0, "操作成功");

    private  int state;

    private  String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    private  CommodityEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public  static CommodityEnum indexOf(int index){
        for (CommodityEnum state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return  null;
    }
}
