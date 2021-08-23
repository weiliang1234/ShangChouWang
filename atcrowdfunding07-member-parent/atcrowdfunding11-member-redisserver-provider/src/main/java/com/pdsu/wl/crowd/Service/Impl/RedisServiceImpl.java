package com.pdsu.wl.crowd.Service.Impl;

import com.pdsu.wl.crowd.Service.RedisService;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/8/21 9:02
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加一个Redis的Key,Value键值对
     * @param key
     * @param value
     */
    @Override
    public void setRedisKeyValueRemote(String key, String value) {
        final ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 添加一个带时间的Redis的Key,Value键值对
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    @Override
    public void setRedisKeyValueRemoteWithTimeOut(String key, String value, long time, TimeUnit timeUnit) {
        final ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,time,timeUnit);
    }

    /**
     * 通过key得到value
     * @param key
     * @return
     */
    @Override
    public ResultEntity<String> getRedisStringValueByKey(String key) {
        final ValueOperations valueOperations = redisTemplate.opsForValue();
        final String value = (String) valueOperations.get(key);
        return ResultEntity.successWithData(value);
    }

    /**
     * 通过key删除
     * @param key
     */
    @Override
    public void removeRedisByKeyRemote(String key) {
        redisTemplate.delete(key);
    }
}
