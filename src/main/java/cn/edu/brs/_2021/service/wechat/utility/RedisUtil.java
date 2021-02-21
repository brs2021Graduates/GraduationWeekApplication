package cn.edu.brs._2021.service.wechat.utility;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {
    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public static boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public static void setKey(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void setKey(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            setKey(key, value);
        }
    }

    public static Object getValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public static void deleteKey(String key){
        redisTemplate.delete(key);
    }

    public static void deleteKey (String ...keys){
        redisTemplate.delete(Arrays.asList(keys));
    }

    public static void expire(String key,long time){
        redisTemplate.expire(key,time,TimeUnit.MINUTES);
    }

    @SuppressWarnings("all")
    public static long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

}