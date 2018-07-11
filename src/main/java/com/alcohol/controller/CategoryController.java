package com.alcohol.controller;

import com.alcohol.pojo.*;

import com.alcohol.service.*;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 王磊
 * 2018-07-0  17:08
 */
@Controller
public class CategoryController {
    @Resource
    private CategoryOneService categoryOneService;
    @Resource
    private CategoryTwoService categoryTwoService;
    @Resource
    private CategoryThreeService categoryThreeService;
    @Resource
    private TypeNameService typeNameService;
    @Resource
    private TypeValueService typeValueService;

    //保存一级分类信息
    private List<Categoryone> categoryoneList;
    //保存二级分类信息
    private List<Categorytwo> categorytwoList;
    //保存三级分类信息
    private List<Categorythree> categorythreeList;
    //保存属性
    private List<TypeName> listTypeName;
    //保存属性值
    private List<Typevalue> listTypeValue;

    /**
     * 查询所有的一级分类
     * @return
     */
    @RequestMapping(value="lisOne",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String lisOne(){
        categoryoneList=categoryOneService.getCategoryOneInfo();
        return JSON.toJSONString(categoryoneList);
    }

   /* *//**
     * 根据一级所选分类查询所有的一级分类下的二级分类
     * @param map
     * @return
     *//*
    @RequestMapping(value="listwo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listwo(Map<String,Object> map){
        categorytwoList=categoryTwoService.getCategoryTwoInfo(map);
        return JSON.toJSONString(categorytwoList);
    }*/

    /**
     * 根据二级所选分类查询所有的二级下的三级分类
     * @param map
     * @return
     */
    @RequestMapping(value="listThree",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listThree(Map<String,Object> map){
        categorythreeList=categoryThreeService.getCategoryThreeInfo(map);
        return JSON.toJSONString(categorythreeList);
    }

    /**
     * 根据所选的三级分类查询对应的属性
     * @param map
     * @return
     */
    @RequestMapping(value="listTypeName",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listTypeName(Map<String,Object> map){
        listTypeName=typeNameService.getTypeName(map);
        return JSON.toJSONString(listTypeName);
    }

    /**
     * 根据属性查询出对应的属性值
     * @param map
     * @return
     */
    @RequestMapping(value="listTypeValue",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listTypeValue(Map<String,Object> map){
        listTypeValue=typeValueService.getTypeValue(map);
        return JSON.toJSONString(listTypeValue);
    }

    /**
     * 得到属性和属性值
     * @param request
     * @return
     */
    @RequestMapping(value="/getCategorythreeById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCategorythreeById(HttpServletRequest request){
        Integer id=(Integer) request.getSession().getAttribute("categoryId");
        return JSON.toJSONString(categoryThreeService.getCategorythreeById(id));
    }


    /**
     * 查询三级上的二级
     * @param parentId
     * @return
     */
    @RequestMapping(value="/getCategorytwoBythreeId",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCategorytwoBythreeId(Integer parentId){
        return JSON.toJSONString(categoryTwoService.getCategorytwoBythreeId(parentId));
    }
}
