package com.alcohol.service;

import com.alcohol.pojo.Typevalue;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface TypeValueService {



    /**
     * 根据Id获取值
     * @param typeValueId
     * @return
     */
    Typevalue selIdType(Long typeValueId);
    /**
     * 根据三级查询所有的属性值
     * @param map
     * @return
     */
    List<Typevalue> getTypeValue(Map<String, Object> map);
    /**
     * 根据属性查询对应的属性值
     * @param tv
     * @return
     */
    List<Typevalue> getTypeValuefy(Typevalue tv, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    /**
     * 新增属性值
     * @param tn
     * @return
     */
    int addTypeValue(Typevalue tn);

    /**
     * 判断修改的属性值是否存在
     * @param tv
     * @return
     */
    int updissel(Typevalue tv);

    /**
     * 修改属性值
     * @param tn
     * @return
     */
    int modifyTypeValue(Typevalue tn);
    /**
     * 判断删除的属性值下是否有商品
     * @param tv
     * @return
     */
    int delisshop(Typevalue tv);

    /**
     * 删除属性值
     * @param tv
     * @return
     */
    int delTypeValue(Typevalue tv);
}
