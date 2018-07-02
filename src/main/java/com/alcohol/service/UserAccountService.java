package com.alcohol.service;

import com.alcohol.dto.UserAccountExecution;

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
}
