package com.alcohol.controller;

import com.alcohol.pojo.Image;
import com.alcohol.service.ImageService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ImageController {
    @Resource
    private ImageService imageService;
    private List<Image> lis;

    @ResponseBody
    @RequestMapping(value="lis",produces = "text/html;charset=utf-8")
    public String list(){
        lis=imageService.list();
        return JSON.toJSONString(lis);
    }
}
