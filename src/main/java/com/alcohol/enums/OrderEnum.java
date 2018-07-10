package com.alcohol.enums;

public enum OrderEnum {
    NOFAIL(-1, "下单失败"),
    USEREMPTY(-2,"账号不存在"),
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
    private  OrderEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public  static OrderEnum indexOf(int index){
        for (OrderEnum  state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return  null;
    }
}
