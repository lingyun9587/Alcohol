package com.alcohol.controller;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Typevalue;
import com.alcohol.service.ProductService;
import com.alcohol.service.TypeValueService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private ProductService productService;
    @Resource
    private TypeValueService typeValueService;

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
        Product pro=new Product();
        List<Product> proslist=productService.selAllDESC(pro,1,2);
        PageInfo<Product> page=new PageInfo<Product>(proslist);
        for (int i=0;i<proslist.size();i++){
            pro=proslist.get(i);
            typevalueId=pro.getTypevalueId();
            String[] arr=typevalueId.split(":");
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
     *//*
    @PostMapping(value="/selName")
    @ResponseBody
    public Object selName(Product product,int pan){
        System.out.println("========================"+product.getPanduan());
        String typevalueId="";
        Product pro=new Product();
        List<Product> proslist=new ArrayList<Product>();
        if(pan==0){
            proslist=productService.selAllDESC(product);
        }else if(pan==1){
            proslist=productService.selAll(product);
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int i=0;i<proslist.size();i++){
            pro=proslist.get(i);
            typevalueId=pro.getTypevalueId();
            String[] arr=typevalueId.split(":");
            for (int j = 0; j < arr.length; j++) {
                Typevalue typevalue= typeValueService.selIdType(Long.parseLong(arr[j]));
                pro.getTypeList().add(typevalue);
            }
            map.put("proslist",proslist);
        }
        return map;
    }*/
}
