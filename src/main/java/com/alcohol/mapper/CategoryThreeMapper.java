package com.alcohol.mapper;

import com.alcohol.pojo.Categorythree;

import java.util.List;
import java.util.Map;

/**
 * 三级分类接口
 */
public interface CategoryThreeMapper {

    /**
     * 根据二级查询三级分类
     * @param map
     * @return
     */
    List<Categorythree> getCategoryThreeInfo(Map<String,Object> map);

    /**
     * 查询要添加的分类是否存在
     * @param categorythreeId
     * @return
     */
    int getCategoryThree(String categorythreeId);

    /**
     * 新增三级分类
     * @param ct
     * @return
     */
    int addCategoryThree(Categorythree ct);

    /**
     * 修改
     * @param ct
     * @return
     */
    int updateCategorythree(Categorythree ct);

    /**
     * 删除
     * @param categorythree_id
     * @return
     */
    int delCategorythree(Long categorythree_id);


    /**
     * 点击三级分类,显示属性和属性值,商品
     * @param id
     * @return
     */
    public Categorythree getCategorythreeById(Integer id);


}
