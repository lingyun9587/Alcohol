package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 胡博
 * 用户账户表
 */
@Data
public class Useraccount {
    private Long accountId;
    private String phone;//手机号
    private Long  userId;//用户表id
    private String email;//邮箱
    private String password;//密码
    private Date createTime;//创建时间
    private Date lastTime;//修改时间

    private User user; //用详情信息

}
