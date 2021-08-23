package com.pdsu.wl.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wl
 * @Date 2021/8/20 15:31
 */
@EnableEurekaClient
@SpringBootApplication
public class RedisCrowdMain {
    public static void main(String[] args) {
        SpringApplication.run(RedisCrowdMain.class, args);
    }
}
