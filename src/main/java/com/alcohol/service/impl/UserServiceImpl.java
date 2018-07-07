package com.alcohol.service.impl;

import com.alcohol.mapper.UserMapper;
import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean ZhuCe(Useraccount useraccount) {
        return false;
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @Override
    public List<User> listUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return userMapper.listUser();
    }

    @Override
    public List<User> getNickNameOne(String nickName) {
        return userMapper.getNickNameOne(nickName);
    }

    @Override
    public int updStatus(Long userId,Long status) {
        return userMapper.updStatus(userId,status);
    }
}
