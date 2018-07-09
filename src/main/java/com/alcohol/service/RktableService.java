package com.alcohol.service;

import com.alcohol.pojo.Rktable;

import java.util.List;

public interface RktableService {
    /**
     * 查询全部
     * @return
     */
    List<Rktable> SelAll(String productName);
}
