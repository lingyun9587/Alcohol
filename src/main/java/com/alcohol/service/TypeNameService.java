package com.alcohol.service;

import com.alcohol.pojo.TypeName;

import java.util.List;
import java.util.Map;

public interface TypeNameService {
    /**
     * 根据三级查询所有的属性
     * @param map
     * @return
     */
    List<TypeName> getTypeName(Map<String, Object> map);
}
