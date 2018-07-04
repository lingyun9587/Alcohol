package com.alcohol.mapper;

import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;

public interface UserMapper {

 /**
  * 判断用户名是否存在
  * @param
  * @return
  */
 public int existUserName(String username);

 /**
  * 会员名状态
  * @param userId
  * @return
  */
 public int userNameSatus(long userId);

 /**
  * 注册
  * @param user
  * @return
  */
 public int insertInfo(User user);

 /**
  * 更改会员信息
  * @param user
  * @return
  */
 public int updateInfo(User user);

}
