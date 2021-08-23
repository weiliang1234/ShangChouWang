package com.pdsu.wl.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author wl
 * @Date 2021/8/20 15:35
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate restTemplate;

    @Test
    public void testRedisSet() {

        final ValueOperations<String, String> Operations = restTemplate.opsForValue();
        Operations.set("apple", "red");
    }
}
