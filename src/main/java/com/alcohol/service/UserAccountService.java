package com.alcohol.service;

import com.alcohol.dto.UserAccountExecution;
import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.pojo.Useraccount;

import javax.servlet.http.HttpServletRequest;

public interface UserAccountService {

    /**
     * 用户注册功能
     * @param //useraccount
     * @return
     */
    public UserAccountExecution register(String username, String password);

    /**
     * 用户登陆
     * @param //useraccount
     * @return
     */
    public UserAccountExecution login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    Useraccount getUserById(String username);

    /**
     * 修改用户信息
     * @param useraccount
     * @return
     */
    public UserAccountExecution updateInfo(Useraccount useraccount);

    /**
     * 修改密码
     * @return 返回int修改成功
     */
    int updatePwd(Useraccount useraccount);
}
