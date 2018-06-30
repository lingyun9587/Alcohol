package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 胡博
 * 用户表
 */
@Data
public class User {
    private Long userId;//用户编号
    private String membershipName;//会员名
    private String realName;//真实姓名
    private String nickName;//昵称
    private char sex;//性别
    private Date birthday;//出生日期
    private Long frozen;//用户是否冻结
    private Date createTime;//创建时间
    private Date laseTime;//修改时间
    private Long integral;//积分
    private Long status;//会员名状态
    private Long paymentPassword;//支付密码
}
