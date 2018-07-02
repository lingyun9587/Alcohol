package com.alcohol.service.impl;

import com.alcohol.mapper.UserMapper;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
  /*  @Resource*/
    private UserMapper userMapper;
    public boolean ZhuCe(Useraccount useraccount) {
        return userMapper.ZhuCe(useraccount);
    }
}
