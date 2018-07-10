package com.alcohol.mapper;


import com.alcohol.pojo.Categoryone;

import java.util.List;

/**
 * 一级分类接口
 */
public interface CategoryOneMapper {

    /**
     * 查询一级分类
     */
    List<Categoryone> getCategoryOneInfo();

    /**
     * 查询要添加的分类是否存在
     * @param categoryontName
     * @return
     */
    int getCategoryone(String categoryontName);

    /**
     * 新增一级分类
     * @param co
     * @return
     */
    int addCategoryOne(Categoryone co);

    /**
     * 修改
     * @param co
     * @return
     */
    int updateCategoryone(Categoryone co);

    /**
     * 删除
     * @param categoryone_id
     * @return
     */
    int delCategoryone(Long categoryone_id);
}