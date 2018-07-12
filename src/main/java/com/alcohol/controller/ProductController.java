package com.alcohol.controller;

import com.alcohol.mapper.ImageMapper;
import com.alcohol.pojo.*;
import com.alcohol.service.TypeValueService;
import com.alcohol.service.impl.CommodityServiceImpl;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private CommodityServiceImpl.ProductService productService;
    @Resource
    private TypeValueService typeValueService;
    @Resource
    private ImageMapper imageMapper;

    @RequestMapping(value="/getproductbyId")
    @ResponseBody
    public String getproductbyId(HttpServletRequest request){
        Integer productId=(Integer)request.getSession().getAttribute("productId");
        return JSON.toJSONString(productService.getProductbyId(productId));
    }


    /**
     * 查询全部
     * @return
     */
    @PostMapping(value="/selpro")
    @ResponseBody
    public Object selpro(@RequestParam(value="pageNum",required = false)Integer pageNum,@RequestParam(value="pageSize",required = false)Integer pageSize){
        String typevalueId="";
        Long imgProductId;
        Product pro=new Product();
        List<Product> proslist=productService.selAllDESC(pro,pageNum,pageSize);
        PageInfo<Product> page=new PageInfo<Product>(proslist);
        for (int i=0;i<proslist.size();i++){
            pro=proslist.get(i);
            typevalueId=pro.getTypevalueId();
            imgProductId=pro.getProductId();
            System.out.println("=======================id====================="+imgProductId);
            List<Image> listimg=imageMapper.selImageByProductId(imgProductId);
            System.out.println("=========================集合==================="+listimg.size());
            pro.setImageList(listimg);
            String[] arr=typevalueId.split(",");
            for (int j = 0; j < arr.length; j++) {
                Typevalue typevalue= typeValueService.selIdType(Long.parseLong(arr[j]));
                pro.getTypeList().add(typevalue);
            }
        }
        return JSON.toJSONString(page);
    }

    /**
     * 点击搜素查询
     * @param product
     * @return
     */
    @PostMapping(value="/selName")
    @ResponseBody
    public Object selName(Product product,@RequestParam(value="pan",required = false)int pan,@RequestParam(value="pageNum",required = false)Integer pageNum,@RequestParam(value="pageSize",required = false)Integer pageSize){
        String typevalueId="";
        Long imgProductId;
        Product pro=new Product();
        List<Product> proslist=new ArrayList<Product>();
        if(pan==0){
            proslist=productService.selAll(product,pageNum,pageSize);
        }else if(pan==1){
            proslist=productService.selAllDESC(product,pageNum,pageSize);
        }
        PageInfo<Product> page=new PageInfo<Product>(proslist);
        for (int i=0;i<proslist.size();i++){
            pro=proslist.get(i);
            typevalueId=pro.getTypevalueId();
            imgProductId=pro.getProductId();
            List<Image> listimg=imageMapper.selImageByProductId(imgProductId);
            pro.setImageList(listimg);
            String[] arr=typevalueId.split(",");
            for (int j = 0; j < arr.length; j++) {
                Typevalue typevalue= typeValueService.selIdType(Long.parseLong(arr[j]));
                pro.getTypeList().add(typevalue);
            }
        }
        return JSON.toJSONString(page);
    }

    /**
     * 查询首页的商品
     * @return
     */
    @RequestMapping(value="/getProductByCategory")
    @ResponseBody
    public String getProductByCategory(Integer weight,Integer categoryOne,Integer pageSize){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("weight",weight);
        map.put("categoryOne",categoryOne);
        map.put("pageSize",pageSize);
        return JSON.toJSONString(productService.getProductByCategory(map));
    }

    /**
     * 根据搜索名模糊查询三级分类集合  xcf
     * @param request 作用域
     * @return 分类集合json字符串
     */
    @RequestMapping(value = "getSearch")
    @ResponseBody
    public String getSearch(HttpServletRequest request,String pName){
        //获取前台搜索框的值
        String pName1 = (String)request.getSession().getAttribute("pName");
        //调用service层的方法进行查询
        List<Categorythree> typeList = productService.getCategorythree(pName1);
        //把分类集合转换为json字符串
        String json = JSON.toJSONString(typeList);
        //返回参数
        System.out.println(json);
        return json;
    }

    /**
     * 根据商品名模糊查询商品集合   xcf
     * @param request 作用域
     * @param pName 搜索框的值
     * @return 查询到的商品集合
     */
    @RequestMapping(value = "getProduct")
    @ResponseBody
    public String getProduct(HttpServletRequest request,String pName,int judge,Integer pageIndex,Integer pageSize){
        //调用service层的方法进行查询 sout
        if(pageIndex==null){
            pageIndex=1;
            pageSize=1;
        }
        PageHelper.startPage(pageIndex,pageSize,true);
        List<Product> prList = productService.getProductList(pName,judge);
        PageInfo<Product> page=new PageInfo<Product>(prList);
        //把分类集合转换为json字符串
        String json = JSON.toJSONString(page);
        //展示json数据
        return json;
    }
    /***
     * 后台商品列表
     */
    @RequestMapping(value="backpro",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String listAll(@RequestParam(value = "product_name",required = false) String product_name,
                          @RequestParam(value = "status",required = false) int status,
                          @RequestParam(value = "pageIndex",required = false) int pageIndex,
                          @RequestParam(value = "pageSize",required = false) int  pageSize){
        List<Product> bu=productService.listAll(product_name,status,pageIndex,pageSize);
        PageInfo<Product> pa=new PageInfo<Product>(bu);
        return JSON.toJSONString(pa);
    }
    /***
     * 批量下架
     */
    @RequestMapping(value = "backsxj")
    @ResponseBody
    public String updataStat(@RequestParam(value = "product_id",required = false) int[] noticeId){
        boolean bu=productService.updateStatus(noticeId);
        String jso=null;
        if (bu){
            jso="{\"ers\":\"yes\",\"mesage\":\"下架成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"下架失败\"}";
        }
        return jso;
    }
    /***
     * 批量上架
     */
    @RequestMapping(value = "backsxjsj")
    @ResponseBody
    public String updataStatsj(@RequestParam(value = "product_id",required = false) int[] noticeId){
        boolean bu=productService.updateStatussj(noticeId);
        String jso=null;
        if (bu){
            jso="{\"ers\":\"yes\",\"mesage\":\"上架成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"上架失败\"}";
        }
        return jso;
    }
    /***
     * 批量删除
     */
    @RequestMapping(value = "backdele")
    @ResponseBody
    public String deleStat(@RequestParam(value = "product_id",required = false) int[] noticeId){
        boolean bu=productService.deleStatus(noticeId);
        String jso=null;
        if (bu){
            jso="{\"ers\":\"yes\",\"mesage\":\"删除成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"删除失败\"}";
        }
        return jso;
    }
}
