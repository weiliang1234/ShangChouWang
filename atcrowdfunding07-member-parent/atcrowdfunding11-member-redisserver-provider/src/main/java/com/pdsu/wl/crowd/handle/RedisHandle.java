package com.pdsu.wl.crowd.handle;

import com.pdsu.wl.crowd.Service.RedisService;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/8/21 8:56
 */
@RestController
public class RedisHandle {

    @Autowired
    private RedisService redisService;

    /**
     * 添加一个Redis的Key,Value键值对
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisService.setRedisKeyValueRemote(key, value);
        return ResultEntity.successWithoutData();
    }

    /**
     * 添加一个带超时时间的Redis的Key,Value键值对
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    @RequestMapping("/set/redis/key/value/remote/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeOut(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnix") TimeUnit timeUnit) {

        redisService.setRedisKeyValueRemoteWithTimeOut(key, value, time, timeUnit);
        return ResultEntity.successWithoutData();
    }

    /**
     * 通过key得到value
     * @param key
     * @return
     */
    @RequestMapping("/get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key) {

        final ResultEntity<String> value = redisService.getRedisStringValueByKey(key);
        return value;
    }

    /**
     * 通过key删除redis
     * @param key
     * @return
     */
    @RequestMapping("/remove/redis/by/key/remote")
    ResultEntity<String> removeRedisByKeyRemote(@RequestParam("key") String key) {
        redisService.removeRedisByKeyRemote(key);
        return ResultEntity.successWithoutData();
    }
}
