package com.alcohol.mapper;

import com.alcohol.pojo.Notice;

import java.util.List;

/**
 * 王磊
 */
public interface NoticeMapper {
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
