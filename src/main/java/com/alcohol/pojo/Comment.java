package com.alcohol.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/***
 * 评论张鹏
 */
@Data
public class Comment {
    private Long commentId;
    private Long skuId;//商品id
    @JSONField(format = "yyyy年MM月dd日hh:mm")
    private Date createTime;//评论时间
    private String content;//内容
    private String review;//追评
    private Date downTime;//追评时间
    private Long status;//是否删除
    private String reply_conte;//回复内容
    private Date replyTime;//回复时间
    private Long userId;//用户id

    private Long spareOne;//备用字段：评价类型1.好评2.中评3.差评
    private Sku sku=new Sku();//Sku对象
    private Product product;//商品对象
    private User user;//用户对象

}
