package com.pdsu.wl.crowd.api;

import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/8/21 8:33
 */
@FeignClient("ShangChouWang-crowd-redis")
public interface RedisRemoteService {

    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/set/redis/key/value/remote/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeOut(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnix") TimeUnit timeUnit);

    @RequestMapping("/get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKey(@RequestParam("key") String key);

    @RequestMapping("/remove/redis/by/key/remote")
    ResultEntity<String> removeRedisByKeyRemote(@RequestParam("key") String key);
}
