package com.alcohol.service.impl;

import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.mapper.NoticeMapper;
import com.alcohol.pojo.Notice;
import com.alcohol.service.NoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public List<Notice> all(int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return noticeMapper.list();
    }

    public Notice seleid(int id)throws UserAccountOperationException {
        return noticeMapper.seleid(id);
    }


    public int add(Notice notice)throws UserAccountOperationException {
        return noticeMapper.add(notice);
    }


    public boolean dele(int[] attr)throws UserAccountOperationException {
        return noticeMapper.dele(attr);
    }


    public int upda(Notice notice)throws UserAccountOperationException {
        return noticeMapper.upda(notice);
    }
}
