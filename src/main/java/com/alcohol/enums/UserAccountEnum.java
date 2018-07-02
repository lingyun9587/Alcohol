package com.alcohol.enums;

public enum UserAccountEnum {
    LOGINFAIL(-1, "密码或帐号输入有误"),
    USEREMPTY(-2,"账号不存在"),
    PASSWORDFAIL(-3,"密码不正确"),
    REALFAIL(-4,"验证码错误"),
    NOTFAIL(-5,"其他异常"),
    SUCCESS(0, "操作成功"),
    NULL_AUTH_INFO(-1006, "注册信息为空"),
    ONLY_ONE_ACCOUNT(-1007,"最多只能绑定一个本地帐号");

    private int state;

    private String stateInfo;

    private UserAccountEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserAccountEnum stateOf(int index) {
        for (UserAccountEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
