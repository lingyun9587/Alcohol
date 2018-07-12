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
     * 后台查询所有咨询
     * @return
     */
    List<Notice> listall(int pageNum, int pageSize);
    /***
     * 根据id查
     * @param id
     * @return
     */
    public Notice seleid(int id);
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
     * @param attr
     * @return
     */
    boolean deleNotice(int[] attr);
}
