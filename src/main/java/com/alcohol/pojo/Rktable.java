package com.alcohol.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 胡博
 * 入库记录
 */
@Data
public class Rktable {
    private Long id;
    private Long skuId;
    private long number;//数量
    private Date createTime;//入库时间
}
