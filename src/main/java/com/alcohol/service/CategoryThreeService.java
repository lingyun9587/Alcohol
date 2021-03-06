package com.alcohol.service;

import com.alcohol.pojo.Categorythree;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface CategoryThreeService {


    /**
     * 点击三级分类,显示属性和属性值,商品
     * @param id
     * @return
     */
    public Categorythree getCategorythreeById(Integer id);

    /**
     * 根据二级查询三级分类
     * @param three
     * @return
     */
    List<Categorythree> getCategoryThreeInfo(Categorythree three);
    /**
     * 根据二级查询三级分类分页
     * @param three
     * @return
     */
    List<Categorythree> getCategoryThreeInfofy(Categorythree three, @RequestParam Integer pageNum, @RequestParam Integer pageSize);

    /**
     * 查询要添加的分类是否存在
     * @param ct
     * @return
     */
    int getCategoryThree(Categorythree ct);

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
     * 判断三级分类下是否存在属性值
     * @param three
     * @return
     */
    int delisthree(Categorythree three);


    /**
     * 删除
     * @param three
     * @return
     */
    int delCategorythree(Categorythree three);

    /**
     * 判断三级分类下是否存在属性
     * @param parent
     * @return
     */
    int selthreeisshu(Integer parent);
}
