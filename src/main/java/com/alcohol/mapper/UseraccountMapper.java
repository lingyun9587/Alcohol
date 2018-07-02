package com.alcohol.mapper;

import com.alcohol.pojo.Role;
import com.alcohol.pojo.Useraccount;

import java.util.List;

/**
 * 用户账号接口
 * @陈赓
 */
public interface UseraccountMapper {

    /**
     * 用户注册
     * @param useraccount
     * @return
     */
  int insertUserAccount(Useraccount useraccount);//注册


 /**
  * 根据会员名、手机号、邮箱号，进行登陆
  * @param username
  * @return  返回用户账号
  */
    Useraccount   getUserByUserName(String username);

}