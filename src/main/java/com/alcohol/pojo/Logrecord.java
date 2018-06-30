package com.alcohol.pojo;

import lombok.Data;

/**
 * 日志表
 */
@Data
public class Logrecord {

    private Long logRecordId;
    private Integer logType;  //日志类型
    private Long userId;   //用户id
    private String content;  //操作内容
    private Integer abnormal; //是否异常
}
