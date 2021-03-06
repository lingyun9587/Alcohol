package com.alcohol.controller;

import com.alcohol.dto.CommodityExecution;
import com.alcohol.pojo.*;
import com.alcohol.service.CommodityService;
import com.alcohol.service.SkuValueService;
import com.alcohol.service.UserAccountService;
import com.alcohol.vo.OrderstatusVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单详情控制层
 * 陈赓
 */
@RestController
public class CommodityController {

    @Resource
    private UserAccountService userAccountService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private SkuValueService skuValueService;
    /**
     * 获取用户订单信息
     * @return
     */
    @GetMapping(value = "/getUserOrderInfo")
    public  Object listVoByUser(){
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);//从作用域中获取对象编号
        List<OrderstatusVo> list = commodityService.listVoByUserId(useraccount.getUserId());
        return list;
    }
    @GetMapping( value = "/listCommodityInfo")
    public Object listCommodityInfo(@RequestParam(defaultValue = "1",value = "pageIndex",required = false)Integer pageIndex,
                                    @RequestParam( value = "pageSize",required = false)Integer pageSize,
                                     @RequestParam(value = "status",required = false)Integer status){
        System.out.println(111);
        System.out.println(status);
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);//从作用域中获取对象编号
        Long userId = useraccount.getUserId();  //从作用域中获取对象编号
        System.out.println(userId);
        System.out.println(123);
        //Integer index=Integer.parseInt(pageIndex);
        //PageHelper.startPage(index,2,true,true);
        PageHelper.startPage(pageIndex == null?1:pageIndex,pageSize);
        List<Commodity> list = commodityService.listCommodityInfo(userId,status);
        for (Commodity commodity: list) {
            Sku sku = commodity.getSku();
           String typevalueId=sku.getSkuvalueId();
           String [] arr = typevalueId.split(",");
           if(typevalueId !=null && !"".equals(typevalueId)){
               for (String str:arr) {
                   SkuValue skuvalue=skuValueService.getSkuById(Integer.parseInt(str));
                   commodity.getSku().getProduct().getSkuValues().add(skuvalue);
               }
           }

        }
        System.out.println("======================="+list.size());
        PageInfo<Commodity>  pageInfo = new PageInfo<>(list);
        System.out.println(":::::"+pageInfo.getList());
        System.out.println(list);
        return pageInfo;
    }

    @GetMapping(value = "/removeCommodityId")
    public Object removeId(@RequestParam(value = "commodityId",required = false) Long commodityId,@RequestParam(value = "orderId",required = false) Long orderId){
        Map<String,Object> map = new HashMap<String,Object>();
        if(commodityId == null || orderId == null){
            map.put("success",false);
            map.put("msg","编号不能为空");
        }
        CommodityExecution commodityExecution = null;
        try{
         commodityExecution = commodityService.removeId(orderId,commodityId);
         if(commodityExecution.getState() == 0){
             map.put("success",true);
             map.put("msg",commodityExecution.getStateInfo());
         }else{
             map.put("success",false);
             map.put("msg",commodityExecution.getStateInfo());
         }
        }catch (Exception e){
            map.put("success",false);
            map.put("msg",e.toString());
        }
        return map;
    }
}
