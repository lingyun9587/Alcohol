package com.alcohol.service;

import com.alcohol.pojo.Categoryone;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CategoryOneService {

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

    /**
     *王磊
     *后台查询分类
     */
    public List<Categoryone> getCategoryOneInfofy(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
