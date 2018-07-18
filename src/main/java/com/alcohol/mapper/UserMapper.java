package com.alcohol.mapper;

import com.alcohol.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

 /**
  * 按昵称查询所有用户
  * @return
  */
 public List<User> listUser(@Param("nickName") String nickName);

 /**
  * 禁用启用用户
  * @param userId
  * @param frozen
  * @return
  */
 public int updStatus(@Param("userId") Long userId,@Param("frozen") Long frozen);

}
