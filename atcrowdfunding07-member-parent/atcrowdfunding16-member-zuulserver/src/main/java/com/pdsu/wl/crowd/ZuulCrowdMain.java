package com.pdsu.wl.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wl
 * @Date 2021/8/21 16:22
 */
@EnableEurekaClient
@SpringBootApplication
public class ZuulCrowdMain {

    public static void main(String[] args) {
        SpringApplication.run(ZuulCrowdMain.class, args);
    }
}
