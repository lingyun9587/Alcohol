package com.alcohol.mapper;

import com.alcohol.pojo.Typevalue;

import java.util.List;
import java.util.Map;

public interface TypeValueMapper {

    /**
     * 根据三级查询所有的属性值
     * @param map
     * @return
     */
    List<Typevalue> getTypeValue(Map<String, Object> map);
}
