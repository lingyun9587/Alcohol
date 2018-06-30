package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 胡博
 * 第三方登陆账户表
 */
@Data
public class Partylandingaccount {
    private Long landingId;
    private Long userId;//用户编号
    private Long type;//登录类型
    private String openId;
    private Date createTime;//创建时间
}
