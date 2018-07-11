package com.alcohol.mapper;

import com.alcohol.pojo.Categorytwo;

import java.util.List;
import java.util.Map;

/**
 * 二级分类接口
 */
public interface CategoryTwoMapper {

    /**
     * 查询二级分类下拉
     * @param ct
     * @return
     */
    List<Categorytwo> getCategoryTwoInfo(Categorytwo ct);
    /**
     * 查询二级分类分页
     * @param ct
     * @return
     */
    List<Categorytwo> getCategoryTwoInfofy(Categorytwo ct);


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
     * 判断二级分类下是否有三级分类
     * @param two
     * @return
     */
    int delistwo(Categorytwo two);

    /**
     * 删除
     * @param two
     * @return
     */
    int delCategorytwo(Categorytwo two);
}
