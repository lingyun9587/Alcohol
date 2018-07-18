package com.alcohol.service;

import com.alcohol.pojo.Categorytwo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface CategoryTwoService {
    /**
     * 查询一级下的二级
     * @param ct
     * @return
     */
    List<Categorytwo> getCategoryTwoInfo(Categorytwo ct);
    /**
     * 查询二级分类分页
     * @param ct
     * @return
     */
    List<Categorytwo> getCategoryTwoInfofy(Categorytwo ct, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

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
    /**
     * 查询二级分类下是否有三级分类
     * @param parent
     * @return
     */
    int seltwoisthree(Integer parent);
}
