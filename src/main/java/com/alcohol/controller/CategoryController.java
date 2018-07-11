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
}
