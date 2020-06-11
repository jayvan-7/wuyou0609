package com.zb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RedisAPI
 *
 * @author bdqn_shang
 * @date 2018-1-10
 */
@Component
public class RedisUtils {

    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set key and value to redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        return true;
    }

    /**
     * set key and value to redis
     *
     * @param key
     * @param seconds 有效期
     * @param value
     * @return
     */
    public boolean set(String key, long seconds, String value) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value,seconds);
        return true;
    }

    /**
     * 判断某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        Object value = vo.get(key);
        return EmptyUtils.isEmpty(value) ? false : true;
    }

    public Object get(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    public void delete(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public boolean validate(String token) {
        return exist(token);
    }
    //=========BoundListOperations 用法 End============
    //lock:1 -->abcdef
    //lock:2--> abck
    //上锁的方法
    public boolean lock(final String key){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer redisSerializer=new StringRedisSerializer();
                byte [] keyByte = redisSerializer.serialize(key);
                byte [] valueByte = redisSerializer.serialize("lock");
                boolean val = redisConnection.setNX(keyByte , valueByte);
                //setnx命令执行成功， 防止程序死锁 ， 添加锁的有效期时间
                if(val){
                    redisConnection.expire(keyByte,60);
                }
                return val;
            }
        });
    }

    //解锁的方法
    public void unlock(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.delete(key);
    }
}
