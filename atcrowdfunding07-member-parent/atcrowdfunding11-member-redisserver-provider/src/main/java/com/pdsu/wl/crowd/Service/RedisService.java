package com.pdsu.wl.crowd.Service;

import com.pdsu.wl.crowd.util.ResultEntity;

import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/8/21 9:00
 */
public interface RedisService {
    /**
     * 455
     * @param key
     * @param value
     */
    void setRedisKeyValueRemote(String key, String value);

    void setRedisKeyValueRemoteWithTimeOut(String key, String value, long time, TimeUnit timeUnit);

    ResultEntity<String> getRedisStringValueByKey(String key);

    void removeRedisByKeyRemote(String key);
}
