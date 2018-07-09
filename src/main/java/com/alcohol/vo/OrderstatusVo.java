package com.alcohol.vo;

import lombok.Data;

/**
 * 前端展示扩展类 订单状态
 */
@Data
public class OrderstatusVo {

    private Long orderStatusId;  //订单状态
    private String orderStatusName; //订单名称
    private Integer number = 0; //订单数量

}
