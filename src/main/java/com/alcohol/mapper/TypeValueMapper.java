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

    /**
     * 新增属性值
     * @param tn
     * @return
     */
    int addTypeValue(Typevalue tn);

    /**
     * 修改属性值
     * @param tn
     * @return
     */
    int modifyTypeValue(Typevalue tn);

    /**
     * 删除属性值
     * @param typeValueId
     * @return
     */
    int delTypeValue(Long typeValueId);
    /**
     * 根据Id获取值
     * @param typeValueId
     * @return
     */
    Typevalue selIdType(Long typeValueId);
}
