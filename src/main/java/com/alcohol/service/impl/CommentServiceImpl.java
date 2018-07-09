package com.alcohol.service.impl;

import com.alcohol.mapper.CommentMapper;
import com.alcohol.pojo.Comment;
import com.alcohol.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listComment(Map<String,Object> map) {
        PageHelper.startPage((Integer)map.get("pageNum"),(Integer)map.get("pageSize"),true,true);
        return commentMapper.listComment(map);
    }

    @Override
    public int upComment(Comment comment) {
        return commentMapper.upComment(comment);
    }

    @Override
    public List<Integer> getCommentCountById(Integer id){
        return commentMapper.getCommentCountById(id);
    }
}
