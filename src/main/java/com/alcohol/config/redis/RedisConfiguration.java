package com.alcohol.config.redis;

import com.alcohol.cache.JedisPoolWriper;
import com.alcohol.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 */
@Configuration
public class RedisConfiguration {
    @Value("${redis.hostname}")
    private String hostname;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.pool.maxActive}")
    private int maxTotal;

    @Value("${redis.pool.minIdle}")
    private int maxIdle;

    @Value("${redis.pool.maxWait}")
    private long maxWaitMillis;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPoolWriper jedisPoolWriper;

    @Autowired
    private JedisUtil jedisUtil;


    /**
     * 创建redis连接池设置
     */
    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();

        //控制可以分配多少个jedis实例
        jedisPoolConfig.setMaxTotal(maxTotal);
        //连接池中最多可空闲maxIdle个连接,这里取值20
        //表示即使没有数据库连接时依然可以保持20空闲的连接
        //而不被清除，随时处于待命状态
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最大连接等待时间：当没有可用连接时
        //连接池等待连接被归还的最大时间(毫秒)，超出抛异常
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        //在获取连接时检查有效性
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }


    /**
     * 创建Redis连接池，并做相关配置
     */
    @Bean(name="jedisWritePool")
    public JedisPoolWriper createJedisPoolWriper(){
      JedisPoolWriper jedisPoolWriper=new JedisPoolWriper(jedisPoolConfig,hostname,port);
      return  jedisPoolWriper;
    }


    /**
     * 创建Redis工具类，封装好Redis的连接以进行相关的操作
     */
    @Bean(name="jedisUtil")
    public JedisUtil createJedisUtil(){
       JedisUtil jedisUtil=new JedisUtil();
       jedisUtil.setJedisPool(jedisPoolWriper);
       return jedisUtil;
    }

    /**
     * Redis的key操作
     */
    @Bean(name="jedisKeys")
    public JedisUtil.Keys createJedisKeys(){
        JedisUtil.Keys jedisKeys=jedisUtil.new Keys();
        return jedisKeys;
    }

    /**
     * Redis的Strings操作
     */
    @Bean(name="jedisStrings")
    public JedisUtil.Strings createJedisStrings(){
        JedisUtil.Strings jedisStrings=jedisUtil.new Strings();
        return jedisStrings;
    }

    /**
     * Redis的Lists操作
     */
    @Bean(name="jedisLists")
    public JedisUtil.Lists createJedisLists(){
        JedisUtil.Lists jedisLists=jedisUtil.new Lists();
        return jedisLists;
    }

    /**
     * Redis的Sets操作
     */
    @Bean(name="jedisSets")
    public JedisUtil.Sets createJedisSets(){
        JedisUtil.Sets jedisSets=jedisUtil.new Sets();
        return jedisSets;
    }

    /**
     * Redis的Hash操作
     */
    @Bean(name="jedisHashs")
    public JedisUtil.Hash createJedisHash(){
        JedisUtil.Hash jedisHash=jedisUtil.new Hash();
        return jedisHash;
    }
}
