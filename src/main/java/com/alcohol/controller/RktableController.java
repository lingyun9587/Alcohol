package com.alcohol.controller;

import com.alcohol.pojo.Rktable;
import com.alcohol.service.RktableService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/proc")
public class RktableController {
    @Resource
    private RktableService rktableService;
    /**
     * 入库记录查询全部
     * @param productName
     * @return
     */
    @PostMapping(value="/listSell")
    @ResponseBody
    public Object listSell(@RequestParam(value="productName",required = false)String productName, @RequestParam(value="pageNum",required = false)Integer pageNum, @RequestParam(value="pageSize",required = false)Integer pageSize){
        System.out.println("============productName"+productName);
        System.out.println("============pageNum"+pageNum);
        System.out.println("============pageSize"+pageSize);
        List<Rktable> listRk=rktableService.SelAll(productName,pageNum,pageSize);
        PageInfo<Rktable> page=new PageInfo<Rktable>(listRk);
        return page;
    }
}
