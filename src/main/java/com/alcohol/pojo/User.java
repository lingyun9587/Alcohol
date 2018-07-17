package com.alcohol.pojo;

import com.alcohol.util.LongJsonDeserializer;
import com.alcohol.util.LongJsonSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 胡博
 * 用户表
 */
@Data
public class User {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;//用户编号
    private String membershipName;//会员名
    private String realName;//真实姓名
    private String nickName;//昵称
    private char sex;//性别
    @JSONField
    @DateTimeFormat
    private Date birthday;//出生日期
    private Long frozen;//用户是否冻结
    @JSONField
    @DateTimeFormat
    private Date createTime;//创建时间
    @JSONField
    @DateTimeFormat
    private Date lastTime;//修改时间
    private Long integral;//积分
    private Long paymentPassword;//支付密码

    private List<Role> roles; //角色集合
}
