package com.alcohol.controller;

import com.alcohol.util.KdniaoTrackQueryAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogisticsController {

    KdniaoTrackQueryAPI kdniaoTrackQueryAPI=new KdniaoTrackQueryAPI();

    @ResponseBody
    @RequestMapping(value="LogisticsSelect",produces = "text/html;charset=utf-8")
    public String LogisticsSelect(String wlGs,String wlnumber){
        String json="";
        try {
            //调用物流查询接口
            json=kdniaoTrackQueryAPI.getOrderTracesByJson(wlGs, wlnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }

}
