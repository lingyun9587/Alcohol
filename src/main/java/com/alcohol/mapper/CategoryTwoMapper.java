package com.alcohol.mapper;

import com.alcohol.pojo.Categorytwo;

import java.util.List;
import java.util.Map;

/**
 * 二级分类接口
 */
public interface CategoryTwoMapper {

    /**
     * 查询一级下的二级
     * @param map
     * @return
     */
    List<Categorytwo> getCategoryTwo(Map<String,Object> map);

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
     * @param categorytwo_id
     * @return
     */
    int delCategorytwo(Long categorytwo_id);

    /**
     * 查询三级上的二级
     * @param id
     * @return
     */
     Categorytwo getCategorytwoBythreeId(Integer id);
}
