package com.alcohol.service;

import com.alcohol.pojo.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 查询所有咨询
     * @return
     */
    List<Notice> list();
}
