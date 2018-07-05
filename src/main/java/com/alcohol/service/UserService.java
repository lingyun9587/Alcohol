package com.alcohol.service;

import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;

import java.util.List;
import java.util.Map;

public interface UserService {
    public boolean ZhuCe(Useraccount useraccount);
    /**
     * 查询所有用户
     * @return
     */
    public List<User> listUser(Integer pageNum, Integer pageSize);
    /**
     * 按昵称查找
     * @param nickName
     * @return
     */
    public List<User> getNickNameOne(String nickName);

    /**
     * 禁用启用用户
     * @param userId
     * @param status
     * @return
     */
    public int updStatus(Long userId,Long status);
}
