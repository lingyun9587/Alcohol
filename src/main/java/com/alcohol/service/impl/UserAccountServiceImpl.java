package com.alcohol.service.impl;

import com.alcohol.dto.UserAccountExecution;
import com.alcohol.enums.UserAccountEnum;
import com.alcohol.exceptions.UserAccountOperationException;
import com.alcohol.mapper.PermissionMapper;
import com.alcohol.mapper.UserMapper;
import com.alcohol.mapper.UseraccountMapper;
import com.alcohol.pojo.Permission;
import com.alcohol.pojo.User;
import com.alcohol.pojo.Useraccount;
import com.alcohol.service.UserAccountService;
import com.alcohol.util.IDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.util.Date;

@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UseraccountMapper useraccountMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserAccountExecution register(String username, String password)throws  UserAccountOperationException {
        if(userMapper.existUserName(username)>0){
         return new UserAccountExecution(UserAccountEnum.ONLY_ONE_ACCOUNT);
        }
        int result = 0;
        int result1 = 0;
        int result2 = 0;
        String memId =IDUtil.getBianHao(); //获取的会员名
        System.out.println("会员名"+memId);
        long userId = IDUtil.SnowflakeIdWorker();  //设置用户id
        System.out.println("yonghuId"+userId);

        User user = new User();
        user.setUserId(userId);
        user.setMembershipName(memId);
        user.setNickName("傲慢小胖妞");
        user.setFrozen(0L);
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        user.setIntegral(0L);
        user.setStatus(0L);

        Useraccount useraccount = new Useraccount();
        Md5Hash md5 = new Md5Hash(password);
        useraccount.setUserId(userId);
        useraccount.setPhone(username);   //设置手机号
        useraccount.setPassword(md5.toString());  //设置密码
        useraccount.setCreateTime(new Date());
        useraccount.setLastTime(new Date());


        Permission permission = new Permission();
        permission.setUserId(userId);
        permission.setRoleId(1L);


        try{
         result = userMapper.insertInfo(user);
         result1 = useraccountMapper.insertUserAccount(useraccount);
         result2 = permissionMapper.insertInfo(permission);
         if(result <=0 || result1 <= 0 || result2 <= 0){
             return new UserAccountExecution(UserAccountEnum.NOTFAIL);
         }else{
             return new UserAccountExecution(UserAccountEnum.SUCCESS);
         }
        }catch (UserAccountOperationException e){
            throw new UserAccountOperationException(e.toString());
        }
    }

    @Override
    public UserAccountExecution login(String username,String password,HttpServletRequest request)throws UserAccountOperationException {
        if ( username == null || password ==null){
            return new UserAccountExecution(UserAccountEnum.NULL_AUTH_INFO);
        }
        try{
        Subject subject =  SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        subject.login(usernamePasswordToken);
            if(subject.hasRole("用户")) {
                System.out.println( "有用户权限");
            }
            if(subject.hasRole("店家")) {
                System.out.println( "有店家权限");
            }
            if(subject.hasRole("管理员")) {
                System.out.println( "有管理员权限");
            }
        } catch (UnknownAccountException ua){
            return new UserAccountExecution(UserAccountEnum.USEREMPTY);
        } catch (IncorrectCredentialsException ic){
          return new UserAccountExecution(UserAccountEnum.PASSWORDFAIL);
        } catch (Exception e){
            throw new UserAccountOperationException("用户登陆错误"+e.toString());
        }
        return new UserAccountExecution(UserAccountEnum.SUCCESS);
    }

    @Override
    public Useraccount getUserById(String username) {
        return useraccountMapper.getUserById(username);
    }

    @Override
    public UserAccountExecution updateInfo(Useraccount useraccount)throws  UserAccountOperationException {
        int result = 0;
        int result1 = 0;

        try{
            result = useraccountMapper.updateInfo(useraccount);
            result1 = userMapper.updateInfo(useraccount.getUser());
            if(result <=0 || result1 <=0){
                return new UserAccountExecution(UserAccountEnum.NOTFAIL);
            }else{
                return new UserAccountExecution(UserAccountEnum.SUCCESS);
            }
        }catch (UserAccountOperationException e){
            throw new UserAccountOperationException(e.toString());
        }
    }
}
