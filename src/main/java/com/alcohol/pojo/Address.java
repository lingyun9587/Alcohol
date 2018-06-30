package com.alcohol.pojo;

import lombok.Data;

/***
 * 地址表张鹏
 */
@Data
public class Address {
    private Long addressId;
    private Long userId;
    private String consignee;//收货人
    private String phone;
    private String province;//省
    private String city;//市
    private String area;//区
    private String street;//街道
    private Long defaults;//是否默认地址,关键字与数据字段名一致,多加个s
    private Long code;//邮编
    private Long isDel;//是否删除
}
