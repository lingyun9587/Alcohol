package com.alcohol.service;

import com.alcohol.pojo.Categorytwo;

import java.util.List;
import java.util.Map;

public interface CategoryTwoService {
    /**
     * 查询一级下的二级
     * @param map
     * @return
     */
    List<Categorytwo> getCategoryTwoInfo(Map<String, Object> map);

    /**
     * 查询要添加的分类是否存在
     * @param categorytwoName
     * @return
     */
    int getCategoryTwo(String categorytwoName);

    /**
     * 新增二级分类
     * @param ct
     * @return
     */
    int addCategoryTwo(Categorytwo ct);

    /**
     * 修改
     * @param ct
     * @return
     */
    int updateCategorytwo(Categorytwo ct);

    /**
     * 删除
     * @param id
     * @return
     */
    int delCategorytwo(Long categorytwo_id);
}