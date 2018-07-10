package com.alcohol.service.impl;

import com.alcohol.mapper.NoticeMapper;
import com.alcohol.pojo.Notice;
import com.alcohol.service.NoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    /***
     * 前台查
     * @return
     */
    public List<Notice> list() {
        return noticeMapper.list();
    }
    /***
     * 后台查
     * @return
     */
    public List<Notice> listall(int pageNum, int pageSize) {
        try{
            PageHelper.startPage(pageNum, pageSize, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return noticeMapper.listall();
    }
    /***
     * 根据id查
     * @param id
     * @return
     */
    public Notice seleid(int id) {
        return noticeMapper.seleid(id);
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
     * @param attr
     * @return
     */
    public boolean deleNotice(int[] attr) {
        return noticeMapper.deleNotice(attr);
    }
}
