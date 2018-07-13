package com.alcohol.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TempSkuName {

    private String skuTypeTitle;
    private String skuTypeKey;
    private Integer skuValueLen;

    private List<TempSkuValue> skuValues;
}
