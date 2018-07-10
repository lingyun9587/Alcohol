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
    public List<Notice> list();

    /***
     * 根据id查
     * @param id
     * @return
     */
    public Notice seleid(int id);

    /***
     * 新增
     * @param notice
     * @return
     */
    public int add(Notice notice);

    /***
     * 删除
     * @param attr
     * @return
     */
    public boolean dele(int[] attr);

    /***
     * 修改
     * @param notice
     * @return
     */
    public int upda(Notice notice);
}
