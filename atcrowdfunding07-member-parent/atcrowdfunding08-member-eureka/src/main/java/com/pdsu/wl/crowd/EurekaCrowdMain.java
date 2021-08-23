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
public class EurekaCrowdMain {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCrowdMain.class, args);
    }
}
