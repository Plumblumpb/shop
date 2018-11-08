package com.plumblum.shop_cache.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 17:35
 * @Description:
 */
@Repository("redisDao")
public class RedisDao {

    @Autowired
    private JedisPool jedisPool;

    public Jedis init(){
       return this.jedisPool.getResource();
    }


    
    public void set(String key, String value) {
        this.init().set(key, value);
    }


    public String get(String key) {
        return this.init().get(key);
    }


    public void delete(String key) {
        this.init().del(key);
    }
}
