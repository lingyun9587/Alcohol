package com.alcohol.service;

import com.alcohol.dto.UserAccountExecution;
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
     * 用户修改密码
     * @param useraccount
     * @return
     */
    public int updatePwd(Useraccount useraccount);
}
