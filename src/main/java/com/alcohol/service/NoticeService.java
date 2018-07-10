package com.alcohol.service;

import com.alcohol.pojo.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 查询所有咨询
     * @return
     */
    public List<Notice> all(int pageNum, int pageSize);
    public Notice seleid(int id);
    public int add(Notice notice);
    public boolean dele(int[] attr);
    public int upda(Notice notice);
}
