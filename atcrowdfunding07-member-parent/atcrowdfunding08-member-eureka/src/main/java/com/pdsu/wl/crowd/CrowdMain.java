package com.pdsu.wl.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wl
 * @Date 2021/8/13 19:33
 */
@EnableEurekaServer
@SpringBootApplication
public class CrowdMain {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMain.class, args);
    }
}
