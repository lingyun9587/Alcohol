package com.alcohol.service.impl;

import com.alcohol.dto.UserAccountExecution;
import com.alcohol.enums.UserAccountEnum;
import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.mapper.UseraccountMapper;
import com.alcohol.service.UserAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
    @Resource
    private UseraccountMapper useraccountMapper;

    @Override
    public UserAccountExecution register(String username, String password) {
        return null;
    }

    @Override
    public UserAccountExecution login(String username,String password,HttpServletRequest request)throws UserAccountOperationException {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if ( username == null || password ==null){
            return new UserAccountExecution(UserAccountEnum.NULL_AUTH_INFO);
        }
        try{
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                return new UserAccountExecution(UserAccountEnum.USEREMPTY);
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                return new UserAccountExecution(UserAccountEnum.PASSWORDFAIL);
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                return new UserAccountExecution(UserAccountEnum.REALFAIL);
            } else {
                System.out.println("else -- >" + exception);
                throw new UserAccountOperationException("用户登陆错误:"+exception);
            }
         }
        }catch (Exception e){
            throw new UserAccountOperationException("用户登陆错误"+e.toString());
        }
        Subject subject =  SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        subject.login(usernamePasswordToken);
        return new UserAccountExecution(UserAccountEnum.SUCCESS);
    }
}
