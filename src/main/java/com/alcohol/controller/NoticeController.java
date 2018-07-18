package com.alcohol.controller;

import com.alcohol.pojo.Notice;
import com.alcohol.service.NoticeService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    /**
     * 查询所有的咨询
     * @return
     */
    @RequestMapping(value="IndexlistNotice",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String IndexlistNotice(){
        List<Notice> lis=noticeService.list();
        return JSON.toJSONString(lis);
    }

    /**
     * 查询所有的咨询
     * @return
     */
    @RequestMapping(value="listNotice",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String listNotice(@RequestParam(value = "pageIndex",required = false) int pageIndex,
                             @RequestParam(value = "pageSize",required = false) int  pageSize){
        List<Notice> lis=noticeService.listall(pageIndex,pageSize);
        PageInfo<Notice> pa=new PageInfo<Notice>(lis);
        return JSON.toJSONString(pa);
    }
    /***
     * 删除
     * @param noticeId
     * @return
     */
    @RequestMapping(value="delenot",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String delt(@RequestParam(value = "noticeId",required = false) int[] noticeId){
        boolean bu=noticeService.deleNotice(noticeId);
        String jso=null;
        if (bu){
            jso="{\"ers\":\"yes\",\"mesage\":\"删除成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"删除失败\"}";
        }
        return jso;
    }
    /***
     * 新增
     * @param notice
     * @return
     */
    @RequestMapping(value="insertnot",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String insert(Notice notice){
        notice.setCreateTime(new Date());
        int shan=noticeService.addNotice(notice);
        String jso=null;
        if (shan>0){
            jso="{\"ers\":\"yes\",\"mesage\":\"新增成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"新增失败\"}";
        }
        return jso;
    }

    /***
     * 根据id查
     * @param notice_id
     * @return
     */
    @RequestMapping(value="seidnot",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String seid(@RequestParam(value = "notice_id",required = false) int notice_id){
        Notice notice=noticeService.seleid(notice_id);
     return JSON.toJSONString(notice);
    }
    /***
     * 修改
     * @param notice
     * @return
     */
    @RequestMapping(value="updanot",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String upda(Notice notice){
        int shan=noticeService.updateNotice(notice);
        String jso=null;
        if (shan>0){
            jso="{\"ers\":\"yes\",\"mesage\":\"修改成功\"}";
        }else {
            jso="{\"ers\":\"no\",\"mesage\":\"修改失败\"}";
        }
        return jso;
    }

}
