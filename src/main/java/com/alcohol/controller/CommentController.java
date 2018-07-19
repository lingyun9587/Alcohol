package com.alcohol.controller;

import com.alcohol.pojo.Comment;
import com.alcohol.pojo.SkuValue;
import com.alcohol.service.CommentService;
import com.alcohol.service.SkuValueService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private SkuValueService skuValueService;

    @RequestMapping(value = "/listComment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public Object listComment(Integer pageNum,Integer pageSize,Integer spareOne,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("spareOne",spareOne);
        if(pageNum==null){
            pageNum=1;
            pageSize=5;
        }
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        Integer productId=(Integer)request.getSession().getAttribute("productId");
        map.put("productId",productId);
        List<Comment> clist = commentService.listComment(map);
        for (int i=0;i<clist.size();i++){
            String skuvalueId=clist.get(i).getSku().getSkuvalueId();
            if(skuvalueId.length()>0){
                String[] arr=skuvalueId.split(",");
                List<SkuValue> SkuValueList=new ArrayList<SkuValue>();
                SkuValue skuvalue=null;
                for (int j = 0; j < arr.length; j++) {
                    skuvalue=skuValueService.getSkuById(Integer.valueOf(arr[j]));
                    SkuValueList.add(skuvalue);
                }
                clist.get(i).getSku().setSkuValueList(SkuValueList);
            }

        }
        PageInfo<Comment> page=new PageInfo<Comment>(clist);
        return JSON.toJSONString(page);
    }

    /**
     * 得到每个商品好评,中评,差评的个数
     * @return
     */
    @RequestMapping(value = "/getCommentCountById",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCommentCountById(HttpServletRequest request){
        Integer productId=(Integer)request.getSession().getAttribute("productId");
        List<Integer> list=commentService.getCommentCountById(productId);
        return JSON.toJSONString(list);
    }


    @RequestMapping(value="/getSkuByProductId")
    @ResponseBody
    public String getSkuBiProductId(String value,HttpServletRequest request){
        Integer productId=(Integer)request.getSession().getAttribute("productId");
        return JSON.toJSONString(skuValueService.getSkuBiProductId(value,productId));
    }
    /**
     * 回复单条用户评价
     * @param com
     * @return
     */
    @RequestMapping(value = "/getUpdateComment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public Object getUpdateComment(@Valid Comment com){
        //System.out.println("gwernrtgherg");
        int a = commentService.upComment(com);
        if (a>0){
            return "{\"message\":\"ok\"}";
        }else {
            return "{\"message\":\"no\"}";
        }
    }

    /**
     * 批量回复用户评价
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/getUpdateListComment",produces = "text/html;charset=utf-8")
    @ResponseBody
    public Object getUpdateListComment(@RequestParam(value = "commentId",required = false) int[] commentId, @RequestParam(value = "reply_conte",required = false) String reply_conte){
        int a=commentService.upListComment(commentId,reply_conte);
        String upc=null;
        if (a>0){
            upc="{\"message\":\"ok\"}";
        }else {
            upc="{\"message\":\"no\"}";
        }
        return upc;
    }
    @RequestMapping(value = "/upgStock",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String upgStock(Integer skuId,Integer number){
        String json="";
        int x=skuValueService.upgStock(skuId,number);
        if(x>0){
            json="{\"mes\":\"修改成功\"}";
        }else{
            json="{\"mes\":\"修改失败\"}";
        }

        return JSON.toJSONString(json);
    }
    @RequestMapping(value = "udai_order_comment.html")
    public Object udai_order_comment(){
        return "udai_order_comment.html";
    }
}
