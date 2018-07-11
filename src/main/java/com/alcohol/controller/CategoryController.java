package com.alcohol.controller;

import com.alcohol.pojo.*;

import com.alcohol.service.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 新增一级分类
     * @param one
     * @return
     */
    @ResponseBody
    @RequestMapping(value="insOne",produces = "text/html;charset=utf-8")
    public String insOne(Categoryone one){
        int con=0;
        String name=one.getCategoryontName();
        //0失败1成功2已存在
        //查询是否存在
        con=categoryOneService.getCategoryone(name);
        if(con>0){
            con=2;
        }else{
            //新增
            con=categoryOneService.addCategoryOne(one);
        }
        return JSON.toJSONString(con);
    }

    /**
     * 查询所有一级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getCategoryOneInfo",produces = "text/html;charset=utf-8")
    public String getCategoryOneInfo(){
        categoryoneList=categoryOneService.getCategoryOneInfo();
        return JSON.toJSONString(categoryoneList);
    }

    /**
     * 新增二级分类
     * @param ct
     * @return
     */
    @ResponseBody
    @RequestMapping(value="addCategoryTwo",produces = "text/html;charset=utf-8")
    public String addCategoryTwo(Categorytwo ct){
        int add=0;
        String categoryTwoName=ct.getCategorytwoName();
        add=categoryTwoService.getCategoryTwo(categoryTwoName);
        if(add>0){
            add=2;
        }else{
            add=categoryTwoService.addCategoryTwo(ct);
        }
        return JSON.toJSONString(add);
    }
    //查询一级分类下的二级分类
    @ResponseBody
    @RequestMapping(value="getCategoryTwoInfo",produces = "text/html;charset=utf-8")
    public String getCategoryTwoInfo(Categorytwo ct){
        categorytwoList=categoryTwoService.getCategoryTwoInfo(ct);

        return JSON.toJSONString(categorytwoList);
    }
    /**
     * 新增三级分类
     */
    @ResponseBody
    @RequestMapping(value = "addCategoryThree",produces ="text/html;charset=utf-8" )
    public String ss(Categorythree ct){
        int count=0;
        count=categoryThreeService.getCategoryThree(ct);
        if(count>0){
            count=2;
        }else{
            count=categoryThreeService.addCategoryThree(ct);
        }
        return JSON.toJSONString(count);
    }

    /**
     * 查询三级分类
     * @param three
     * @return
     */
    @ResponseBody
    @RequestMapping(value="categoryThreeInfo",produces = "text/html;charset=utf-8")
    public String categoryThreeInfo(Categorythree three){
        categorythreeList=categoryThreeService.getCategoryThreeInfo(three);

        return JSON.toJSONString(categorythreeList);
    }
    /**
     * 获取属性名与属性值
     */
    @ResponseBody
    @RequestMapping(value="cout",produces = "text/html;charset=utf-8")
    public String cout(TypeName typeName){
        int type=0;//1.存在2.不存在3.新增属性成功4.新增属性失败5.新增属性值成功6.新增属性值失败
        System.out.println(111);
        //判断属性是否存在
        int count=typeNameService.seltnId(typeName);
        if(count>0){
            //属性存在
            type=1;
            System.out.println("属性已存在");
        }else{
            System.out.println("属性不存在");
            //新增属性
            int instype=typeNameService.addTypeName(typeName);
            if(instype>0){
                //新增属性成功
                type=3;
            }else{
                //新增属性失败
                type=4;
            }
        }


        return JSON.toJSONString(type);
    }

    /**
     * 王磊
     * 后台查询所有一级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="listCategorys",produces="text/html;charset=utf-8")
    public String getCategoryOneInfo(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        List<Categoryone> list=categoryOneService.getCategoryOneInfofy(pageNum,pageSize);

        PageInfo<Categoryone> apps=new PageInfo<Categoryone>(list);

        return JSON.toJSONString(apps);
    }

    /**
     * 查询所有一级分类下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getCategoryOneInfos",produces = "text/html;charset=utf-8")
    public String getCategoryOneInfos(){
        categoryoneList=categoryOneService.getCategoryOneInfos();
        return JSON.toJSONString(categoryoneList);
    }

    /**
     * 王磊
     * 后台查询一级分类下的二级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getCategoryTwoInfofy",produces="text/html;charset=utf-8")
    public String getCategoryTwoInfofy(Categorytwo ct,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){

        List<Categorytwo> lis=categoryTwoService.getCategoryTwoInfofy(ct,pageNum,pageSize);


        pageNum=(pageNum-1)*pageSize;
        PageInfo<Categorytwo> apps=new PageInfo<Categorytwo>(lis);
        return JSON.toJSONString(apps);
    }

    /**
     * 查询二级下的三级分类
     * @param ct
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getCategoryThreeInfofy",produces = "text/html;charset=utf-8")
    public String getCategoryThreeInfofy(Categorythree ct,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        List<Categorythree> lists=categoryThreeService.getCategoryThreeInfofy(ct,pageNum,pageSize);
        pageNum=(pageNum-1)*pageSize;
        PageInfo<Categorythree> apps=new PageInfo<Categorythree>(lists);

        return JSON.toJSONString(apps);
    }

    /**
     * 查询三级下的属性
     * @param tn
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getTypeNamefy",produces = "text/html;charset=utf-8")
    public String getTypeNamefy(TypeName tn,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        List<TypeName> lists=typeNameService.getTypeNamefy(tn,pageNum,pageSize);
        PageInfo<TypeName> apps=new PageInfo<TypeName>(lists);
        return JSON.toJSONString(apps);
    }

    /**
     * 查询属性下的属性值
     * @param tv
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getTypeValuefy",produces = "text/html;charset=utf-8")
    public String getTypeValuefy(Typevalue tv,Integer pageNum,Integer pageSize){

        List<Typevalue> lists=typeValueService.getTypeValuefy(tv,pageNum,pageSize);
        PageInfo<Typevalue> apps=new PageInfo<Typevalue>(lists);
        return JSON.toJSONString(apps);
    }

    /**
     * 删除属性值
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delTypeValue",produces = "text/html;charset=utf-8")
    public String delTypeValue(Typevalue tv){
        int num=0;//num等于1有商品，2删除成功3.删除失败
        //判断属性下是否有商品
        int isdel=typeValueService.delisshop(tv);

        if(isdel>0){
            //属性值下有商品不得删除
            num=1;
        }else{
            //如果没有商品删除属性值，
            tv.setIsdel(1);
            int count=typeValueService.delTypeValue(tv);
            if(count>0){
                num=2;
            }else{
                num=3;
            }
        }
        return JSON.toJSONString(num);
    }

    /**
     * 删除属性
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delTypename",produces = "text/html;charset=utf-8")
    public String delTypename(TypeName tn){
        int num=0;//num等于1有商品，2删除成功3.删除失败
        //判断属性下是否有商品
        int isdel=typeNameService.delistypevalue(tn);

        if(isdel>0){
            //属性下属性值
            num=1;
        }else{
            //如果没有属性值删除属性
            tn.setIsdel(1);
            int count=typeNameService.delTypeName(tn);
            if(count>0){
                num=2;
            }else{
                num=3;
            }
        }
        return JSON.toJSONString(num);
    }

    /**
     * 删除三级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delcategorythree",produces = "text/html;charset=utf-8")
    public String delcategorythree(Categorythree three){
        int num=0;//num等于1有商品，2删除成功3.删除失败
        //判断三级分类下是否有属性
        int isdel=categoryThreeService.delisthree(three);
        if(isdel>0){
            //三级分类下有属性不得删除
            num=1;
        }else{
            //如果没有属性删除三级分类
            three.setIsdel(1);
            int count=categoryThreeService.delCategorythree(three);

            if(count>0){
                num=2;
            }else{
                num=3;
            }
        }
        return JSON.toJSONString(num);
    }

    /**
     * 删除二级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delcategorytwo",produces = "text/html;charset=utf-8")
    public String delcategorytwo(Categorytwo two){
        int num=0;//num等于1有商品，2删除成功3.删除失败
        //判断二级分类下是否有三级分类
        System.out.println(two.getCategorytwoId());
        System.out.println(1231231231);
        System.out.println(two.getCategorytwoId());
        //有值但是0
        int isdel=categoryTwoService.delistwo(two);
        System.out.println(isdel);
        if(isdel>0){
            //二级分类下有三级分类不得删除
            num=1;
        }else{
            //如果没有三级分类删除二级分类
            two.setIsdel(1);
            int count=categoryTwoService.delCategorytwo(two);
            if(count>0){
                num=2;
            }else{
                num=3;
            }
        }
        return JSON.toJSONString(num);
    }

    /**
     * 修改一级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updone",produces = "text/html;charset=utf-8")
    public String updone(Categoryone one){
        int isupd=0;//1修改成功2修改失败,3已存在
        //判断修改该的分类的名字是否存在
        String name=one.getCategoryontName();
        isupd=categoryOneService.getCategoryone(name);
        if(isupd>0){
            //分类已存在不能修改
            isupd=3;
        }else{
            //分类不存在可以修改
            isupd=categoryOneService.updateCategoryone(one);
            if(isupd>0){
                //修改成功
                isupd=1;
            }else{
                //修改失败
                isupd=2;
            }
        }
        return JSON.toJSONString(isupd);
    }

    /**
     * 修改二级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updtwo",produces = "text/html;charset=utf-8")
    public String updtwo(Categorytwo two){
        int isupd=0;//1修改成功2修改失败,3已存在
        //判断修改该的分类的名字是否存在
        String name=two.getCategorytwoName();
        isupd=categoryTwoService.getCategoryTwo(name);
        if(isupd>0){
            //分类已存在不能修改
            isupd=3;
        }else{
            //分类不存在可以修改
            isupd=categoryTwoService.updateCategorytwo(two);
            if(isupd>0){
                //修改成功
                isupd=1;
            }else{
                //修改失败
                isupd=2;
            }
        }
        return JSON.toJSONString(isupd);
    }

    /**
     * 修改三级分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updthree",produces = "text/html;charset=utf-8")
    public String updthree(Categorythree three){
        int isupd=0;//1修改成功2修改失败,3已存在
        //判断修改该的分类的名字是否存在

        isupd=categoryThreeService.getCategoryThree(three);
        if(isupd>0){
            //分类已存在不能修改
            isupd=3;
        }else{
            //分类不存在可以修改
            isupd=categoryThreeService.updateCategorythree(three);
            if(isupd>0){
                //修改成功
                isupd=1;
            }else{
                //修改失败
                isupd=2;
            }
        }
        return JSON.toJSONString(isupd);
    }

    /**
     * 修改属性
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updshu",produces = "text/html;charset=utf-8")
    public String updshu(TypeName tn){
        int isupd=0;//1修改成功2修改失败,3已存在
        //判断修改该属性的名字是否存在

        isupd=typeNameService.TypeNameissel(tn);
        if(isupd>0){
            //属性已存在不能修改
            isupd=3;
        }else{
            //属性不存在可以修改
            isupd=typeNameService.modifyTypeName(tn);
            if(isupd>0){
                //修改成功
                isupd=1;
            }else{
                //修改失败
                isupd=2;
            }
        }
        return JSON.toJSONString(isupd);
    }

    /**
     * 修改属性值
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updshuxing",produces = "text/html;charset=utf-8")
    public String updshuxing(Typevalue tv){
        int isupd=0;//1修改成功2修改失败,3已存在
        //判断修改该属性的名字是否存在
        System.out.println(1231231231);
        System.out.println(tv.getTypeValueId());
        System.out.println(tv.getTypeValueName());
        isupd=typeValueService.updissel(tv);
        if(isupd>0){
            //属性已存在不能修改
            isupd=3;
        }else{
            //属性不存在可以修改
            isupd=typeValueService.modifyTypeValue(tv);
            if(isupd>0){
                //修改成功
                isupd=1;
            }else{
                //修改失败
                isupd=2;
            }
        }
        return JSON.toJSONString(isupd);
    }


}
