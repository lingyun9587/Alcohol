package com.alcohol.service.impl;

import com.alcohol.mapper.NoticeMapper;
import com.alcohol.pojo.Notice;
import com.alcohol.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public List<Notice> list() {
        return noticeMapper.list();
    }
}
