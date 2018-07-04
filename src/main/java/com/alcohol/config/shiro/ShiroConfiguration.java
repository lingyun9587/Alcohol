package com.alcohol.config.shiro;


import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @陈赓
 */
@Configuration
public class ShiroConfiguration {

  @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier( "securityManager") SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/*", "anon");
      filterChainDefinitionMap.put("/static/**", "anon");
      filterChainDefinitionMap.put("/static/*.*", "anon");
      filterChainDefinitionMap.put("/user/loginUser", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        filterChainDefinitionMap.put("/udai_address.html", "roles[用户]");
        //filterChainDefinitionMap.put("/udai_paypwd_modify.html", "roles[管理员]");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 配置核心安全事务管理器
     * @param CustomRealm
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("customRealm")CustomRealm CustomRealm){
      System.out.print("-------shiro已经加载");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(CustomRealm);
        return  manager;
    }

    /**
     * 配置自定义的权限登陆器
     * @param //CredentialsMatcher
     * @return
     */
    @Bean( name = "customRealm")
    public CustomRealm  customRealm(@Qualifier("credentialsMatcher")CredentialsMatcher matcher){
         CustomRealm  customRealm = new CustomRealm();
         customRealm.setCredentialsMatcher(matcher);
         return  customRealm;
    }

    /**
     * 配置自定义的密码比较器
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        return new CredentialsMatcher();
    }

    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return  creator;
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
