package com.alcohol.mapper;

import com.alcohol.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评论表
 */
public interface CommentMapper {
    /**
     * 查询全部评论列表
     * @return
     */
    List<Comment> listComment(Map<String, Object> map);

    /**
     * 回复评论
     * @param comment
     * @return
     */
    int upComment(Comment comment);


    /**
     * 得到每个商品好评,中评,差评的个数
     * @param id
     * @return
     */
    List<Integer> getCommentCountById(Integer id);
    /**
     * 批量回复用户评价
     * @param com
     * @return
     */
    int upListComment(@Param("com") int[] com, @Param("reply_conte") String reply_conte);

    /**
     * 用户评论商品
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 添加评论图片
     * @return
     */
    int commentImg(@Param("imagePath")String imagePath,@Param("productId")String productId);

}
