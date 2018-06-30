package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 公告表
 */
@Data
public class Notice {

    private Long noticeId;
    private  String title;  //标题
    private  String link;  //链接
    private  String content; //内容
    private Date createTime;  //创建时间
    private  Integer type;  //类型id
}
