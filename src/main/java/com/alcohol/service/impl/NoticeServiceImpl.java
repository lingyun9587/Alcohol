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
    /**
     * 新增资讯
     * @param inNotice
     * @return
     */
    @Override
    public int addNotice(Notice inNotice) {
        return noticeMapper.addNotice(inNotice);
    }
    /**
     * 修改资讯
     * @param upNotice
     * @return
     */
    @Override
    public int updateNotice(Notice upNotice) {
        return noticeMapper.updateNotice(upNotice);
    }
    /**
     * 删除资讯
     * @param delNotice
     * @return
     */
    @Override
    public int deleteNotice(int delNotice) {
        return noticeMapper.deleteNotice(delNotice);
    }
}
