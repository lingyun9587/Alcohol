package com.alcohol.controller;

import com.alcohol.pojo.*;
import com.alcohol.service.ProductService;
import com.alcohol.service.SkuValueService;
import com.alcohol.service.TypeValueService;
import com.alcohol.service.ImageService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    @Resource
    private ImageService imageService;
    @Resource
    private SkuValueService skuValueService;


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

            List<Image> listimg=imageService.selProductId(imgProductId);
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
            List<Image> listimg=imageService.selProductId(imgProductId);
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
     * 根据搜索名模糊查询三级分类集合   xcf
     * @return 分类集合json字符串
     */
    @RequestMapping(value = "getSearch")
    @ResponseBody
    public String getSearch(String pName){
        //调用service层的方法进行查询
        List<Categorythree> typeList = productService.getCategorythree(pName);
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

    /**
     * 根据分类属性值模糊查询商品集合   xcf
     * @param request 作用域
     * @param typeValueId 分类属性值
     * @return 查询到的商品集合
     */
    @RequestMapping(value = "getTypeProductList")
    @ResponseBody
    public String getTypeProductList(HttpServletRequest request,String typeValueId, int judge, Integer pageIndex, Integer pageSize){
        //调用service层的方法进行查询 sout
        int categoryThree = (int) request.getSession().getAttribute("categoryId");
        String[] typeValueArray = null;
        if(!typeValueId.equals("-1")){
            typeValueArray = typeValueId.split(",");
        }
        if(pageIndex==null){
            pageIndex=1;
            pageSize=1;
        }
        PageHelper.startPage(pageIndex,pageSize,true);
        List<Product> pList = productService.getTypeProductList(categoryThree,typeValueArray,judge);
        System.out.println("集合"+pList.size());
        PageInfo<Product> page=new PageInfo<Product>(pList);
        //把分类集合转换为json字符串
        String json = JSON.toJSONString(page);
        //展示json数据
        return json;
    }

    @RequestMapping(value = "/getrequest")
    @ResponseBody
    public String getrequest(HttpServletRequest request){
        String JSON="";
        String pid="";
        String weight="";
        if(request.getSession().getAttribute("pName")!=null){
            JSON="{\"pid\":\""+request.getSession().getAttribute("pName")+"\"}";
        }
        if(request.getSession().getAttribute("weight")!=null){
            weight=request.getSession().getAttribute("weight").toString();
            JSON="{\"weight\":\""+weight+"\"}";
        }
        return JSON;
    }

    @RequestMapping(value = "/addProduct")
    @ResponseBody
    public String addProduct(Product p,HttpServletRequest request){
        String json="";
        String  skuTypeArr = request.getParameter("skuTypeArr");//sku属性和属性值
        String alreadySetSkuVals1 = request.getParameter("alreadySetSkuVals1");
        String  image = request.getParameter("image");//商品
        String weight = request.getParameter("weightList");//权重
        String [] imageList=image.split(",");
        String [] weightList=weight.split(",");
        List<TempSkuName> list=JSON.parseArray(skuTypeArr,TempSkuName.class);
        List<TempSku> listSku=JSON.parseArray(alreadySetSkuVals1,TempSku.class);
        int x=productService.addProduct(p);//第一步新增商品
        if(x>0){
            for(int i=0;i<imageList.length;i++){
                Image img=new Image();
                img.setImageType("0");
                img.setWeight(Long.parseLong(weightList[i]));//权重
                img.setProductId(p.getProductId());
                img.setImagePath(imageList[i]);//路径
                imageService.addImage(img);
            }
            for (TempSkuName temp:list){
                skuName skuname=new skuName();
                skuname.setProductId(p.getProductId());//商品编号
                skuname.setSkunameeValue(temp.getSkuTypeTitle());//属性名称
                int y=skuValueService.addSkuName(skuname);//新增属性
                for (TempSkuValue skuvalue:temp.getSkuValues()){
                    SkuValue value=new SkuValue();
                    value.setSkuvalueValue(skuvalue.getSkuValueTitle());//属性值
                    value.setWeight(10L);//权重
                    value.setSkunameId(skuname.getSkunameId());//属性Id
                    int z=skuValueService.addSkuValue(value);
                }
            }
            //新增sku
            for(TempSku sku:listSku) {
                Sku s=new Sku();
                s.setProductId(p.getProductId());//商品编号
                s.setStock(sku.getSkuStock().longValue());//库存
                s.setPresentPrice(sku.getSkuPrice());//价格
                s.setOriginalPrice(sku.getSkuPrice()*2);//原价
                String value = sku.getSkuId();//-2,-3
                String arr01="";
                if(value!="propvalids"&&!"propvalids".equals(value)){
                    String[] arr = value.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        Integer valueId = Integer.valueOf(arr[i]);
                        for (TempSkuName temp : list) {
                            for (TempSkuValue skuvalue:temp.getSkuValues()) {
                                if (valueId == skuvalue.getSkuValueId()) {
                                    SkuValue skuvalues=skuValueService.getSkuValueIdByname(p.getProductId(),skuvalue.getSkuValueTitle());
                                    arr01+=skuvalues.getSkuvalueId()+"";
                                    if(i<arr.length-1){
                                        arr01+=",";
                                    }
                                }
                            }
                        }
                    }
                }
                s.setSkuvalueId(arr01);
                int y=skuValueService.addSku(s);
                json="{\"res\":\"yes\",\"mes\":\"商品新增成功\"}";
            }
        }else{
            json="{\"res\":\"no\",\"mes\":\"商品新增失败\"}";
        }
        return json;
    }


    /**
     * 看了又看
     * @param categorythreeId
     * @return
     */
    @RequestMapping(value = "/getProductByCategorythreeId")
    @ResponseBody
    public Object getProductByCategorythreeId(Integer pageIndex,Integer pageSize,Integer categorythreeId){
        PageHelper.startPage(pageIndex,pageSize,true,true);
        List<Product> list=productService.getProductByCategorythreeId(categorythreeId);
        for (int i=0;i<list.size();i++){
            if(list.get(i).getProductName().length()>10){
                String  productName=list.get(i).getProductName().substring(0,10);
                list.get(i).setProductName(productName);
            }
        }
        PageInfo<Product> page=new PageInfo<Product>(list);
        return page;
    }

}
