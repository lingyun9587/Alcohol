package com.alcohol.dto;

import com.alcohol.enums.UserAccountEnum;
import com.alcohol.pojo.Useraccount;

import java.util.List;

public class UserAccountExecution {

    //状态
    private int state;
    //消息
    private String stateInfo;
   //单个对象
    private Useraccount useraccount;
   //多个对象
    private List<Useraccount> useraccounts;
     //失败构造器
    public UserAccountExecution(UserAccountEnum userAccountEnum ){
       this.state = userAccountEnum.getState();
       this.stateInfo = userAccountEnum.getStateInfo();
    }
    //成功的构造器
    public UserAccountExecution(UserAccountEnum userAccountEnum, Useraccount useraccount ){
        this.state = userAccountEnum.getState();
        this.stateInfo = userAccountEnum.getStateInfo();
        this.useraccount = useraccount;
    }
    //成功的构造器
    public UserAccountExecution(UserAccountEnum userAccountEnum, List<Useraccount> useraccounts ){
        this.state = userAccountEnum.getState();
        this.stateInfo = userAccountEnum.getStateInfo();
        this.useraccounts = useraccounts;
    }
}
