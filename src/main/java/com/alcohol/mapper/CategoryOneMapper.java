package com.alcohol.mapper;


import com.alcohol.pojo.Categoryone;
import com.alcohol.pojo.Categorytwo;

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
     * 后台查询一级分类
     * @return
     */
    List<Categoryone> getCategoryOneInfos();

    /**
     * 查询一级分类分页
     */
    List<Categoryone> getCategoryOneInfofy();

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

    /**
     *王磊
     *后台查询分类
     */
    public List<Categoryone> listCategory();

    /**
     * 查询一级分类下是否有二级分类
     * @return
     */
    int seloneistwo(Integer two);
}
