package com.alcohol.mapper;

import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.pojo.Role;
import com.alcohol.pojo.Useraccount;
import org.apache.ibatis.annotations.Param;

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
    Useraccount   getUserByUserName(@Param("username") String username);


    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    Useraccount   getUserById(String username);

    /**
     * 更改用户信息
     * @param useraccount
     * @return
     */
    int updateInfo(Useraccount useraccount);

    /**
     * 修改密码
     * @return 返回int修改成功
     */
    int updatePwd(Useraccount useraccount);
}
