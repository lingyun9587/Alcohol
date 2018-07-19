package com.alcohol.controller;

import com.alcohol.dto.OrderExecution;
import com.alcohol.mapper.UseraccountMapper;
import com.alcohol.pojo.*;
import com.alcohol.service.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class CommentController {
    @Resource
    private OrderService orderService;
    @Resource
    private CommentService commentService;

    @Resource
    private  ImageService imageService;
    @Resource
    private SkuValueService skuValueService;
    @Resource
    private SkuService skuService;
    @Resource
    private UserAccountService userAccountService;
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
            String[] arr=skuvalueId.split(",");
            List<SkuValue> SkuValueList=new ArrayList<SkuValue>();
            SkuValue skuvalue=null;
            for (int j = 0; j < arr.length; j++) {
              skuvalue=skuValueService.getSkuById(Integer.valueOf(arr[j]));
              SkuValueList.add(skuvalue);
            }
            clist.get(i).getSku().setSkuValueList(SkuValueList);
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

    @GetMapping(value="udai_order_comment.html")
    public  Object udai_order_comment(HttpServletRequest request, @RequestParam(value = "commodityId",required = false) Long commodityId){
        if(commodityId!=0){
            request.getSession().setAttribute("commodityId",commodityId);
        }
        return "udai_order_comment";
    }



    @RequestMapping(value = "/GetCommentByorder",method = RequestMethod.POST)
    @ResponseBody
    public String GetCommentByorder(HttpServletRequest request){
        Long commodityId=Long.valueOf(request.getSession().getAttribute("commodityId").toString());
        Order order=orderService.getOeder(commodityId);
        String skuvalueId=order.getCommodities().get(0).getSk().getSkuvalueId();
        String[] arr=skuvalueId.split(",");
        List<SkuValue> SkuValueList=new ArrayList<SkuValue>();
        SkuValue skuvalue=null;
        for (int j = 0; j < arr.length; j++) {
            skuvalue=skuValueService.getSkuById(Integer.valueOf(arr[j]));
            SkuValueList.add(skuvalue);
        }
        order.getCommodities().get(0).getSk().setSkuValueList(SkuValueList);
        return JSON.toJSONString(order);
    }

    /**
     * 用户评论
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertComment",method = RequestMethod.POST)
    public Object insertComment(Comment comment,String imagePath,HttpServletRequest request){
        Map<String,Object>  map = new HashMap<>();
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        Useraccount useraccount = userAccountService.getUserById(userName);

        Date time = new Date();
        comment.setCreateTime(time);
        //获取用户userId
        comment.setUserId(useraccount.getUser().getUserId());
        //获取skuId
        //comment.setSkuId(comment.getSkuId());
        int result=commentService.insertComment(comment);   //新增评论
        String json="";
        if(result>0){
            map.put("mes","mes");
            //如果图片路径不为空则添加图片
            if(imagePath!=null&&imagePath!=""){
                //获取商品编号
                Sku sku=skuService.getById(comment.getSkuId());
               String productId =sku.getProduct().getProductId().toString();
               Image image = new Image();
               image.setImagePath(imagePath);
               image.setImageType("1");
               image.setProductId(Long.valueOf(productId));
               image.setWeight(10L);
                int result1=imageService.addImage(image);    //新增评论图片
                        //commentService.commentImg(imagePath,productId);
            }
            //用户评论成功修改订单状态  没写
            Order order = new Order();
            String orderId=request.getParameter("orderId");
            order.setOrderId(Long.valueOf(orderId));
            order.setStatus(14);
            OrderExecution result13 =  orderService.updateOrder(order);
           // System.out.println(result13.getState());
        }else{
            map.put("mes","失败");
        }
        return map;
    }
}
