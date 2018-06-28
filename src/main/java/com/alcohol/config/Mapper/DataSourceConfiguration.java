package com.alcohol.config.Mapper;


import com.alcohol.util.DESUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * 配置datasource到ioc容器里面
 */
@Configuration
//配置mybaits mapper的扫描路径
@MapperScan("com.ageless.mapper")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDirver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;


    /**
     * 生成与spring-dao.xml对应的bean dataSource
     */
    @Bean(name="dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        //生成datasource实例
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        //配置连接池
            //驱动
            dataSource.setDriverClass(jdbcDirver);
            //数据库连接URL
            dataSource.setJdbcUrl(jdbcUrl);
            //设置用户名
            dataSource.setUser(DESUtils.getDecryptString(jdbcUsername));
            //设置密码
            dataSource.setPassword(DESUtils.getDecryptString(jdbcPassword));
        //配置c3p0私有属性
        //连接池最大线程数
        dataSource.setMaxPoolSize(30);
        //连接池最小线程数
        dataSource.setMinPoolSize(10);
        //关闭连接后不知道commit
        dataSource.setAutoCommitOnClose(false);
        //连接超时时间
        dataSource.setCheckoutTimeout(10000);
        //连接失败重试次数
        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
    }
}
