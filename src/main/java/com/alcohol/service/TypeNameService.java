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

    /**
     * 新增属性
     * @param tn
     * @return
     */
    int addTypeName(TypeName tn);

    /**
     * 修改属性
     * @param tn
     * @return
     */
    int modifyTypeName(TypeName tn);

    /**
     * 删除属性
     * @param typeNameId
     * @return
     */
    int delTypeName(Long typeNameId);
}
