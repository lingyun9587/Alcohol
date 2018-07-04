package com.alcohol.controller;

import com.alcohol.pojo.Notice;
import com.alcohol.service.NoticeService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    private List<Notice> lis;

    /**
     * 查询所有的咨询
     * @return
     */
    @RequestMapping(value="listNotice",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listNotice(){
        lis=noticeService.list();
        return JSON.toJSONString(lis);
    }
}
