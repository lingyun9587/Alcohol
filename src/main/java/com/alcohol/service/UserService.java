package com.alcohol.service;

import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;

import java.util.List;
import java.util.Map;

public interface UserService {
    public boolean ZhuCe(Useraccount useraccount);
    /**
     * 按昵称查询所有用户
     * @return
     */
    public List<User> listUser(String nickName,Integer pageNum, Integer pageSize);

    /**
     * 禁用启用用户
     * @param userId
     * @param status
     * @return
     */
    public int updStatus(Long userId,Long status);
}
