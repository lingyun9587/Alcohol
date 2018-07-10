package com.alcohol.service;

import com.alcohol.pojo.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 查询所有咨询
     * @return
     */
    List<Notice> list();
    /**
     * 新增资讯
     * @param inNotice
     * @return
     */
    int addNotice(Notice inNotice);

    /**0
     * 修改资讯
     * @param upNotice
     * @return
     */
    int updateNotice(Notice upNotice);

    /**
     * 删除资讯
     * @param delNotice
     * @return
     */
    int deleteNotice(int delNotice);
}
