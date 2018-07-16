package com.alcohol.service;

import com.alcohol.pojo.TypeName;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface TypeNameService {
    /**
     * 根据三级查询所有的属性
     * @param map
     * @return
     */
    List<TypeName> getTypeName(Map<String, Object> map);
    /**
     *根据三级分类查询对应的属性
     */
    List<TypeName> getTypeNamefy(TypeName tn, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize);

    /**
     * 新增属性
     * @param tn
     * @return
     */
    int addTypeName(TypeName tn);

    /**
     * 修改属性
     * @param tn
     * @return
     */
    int modifyTypeName(TypeName tn);
    /**
     * 判断删除的属性下是否有属性值
     * @param tn
     * @return
     */
    int delistypevalue(TypeName tn);
    /**
     * 判断要修改的属性名是否存在
     * @param tn
     * @return
     */
    int TypeNameissel(TypeName tn);

    /**
     * 删除属性
     * @param tn
     * @return
     */
    int delTypeName(TypeName tn);
    /**
     * 查询属性是否存在
     */
    int seltnId(TypeName typeName);
    /**
     * 查询新增属性的id
     * @return
     */
    int list();
}
