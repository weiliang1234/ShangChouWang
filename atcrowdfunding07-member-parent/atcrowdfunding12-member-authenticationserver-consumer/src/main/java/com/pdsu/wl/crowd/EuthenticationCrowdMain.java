package com.pdsu.wl.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wl
 * @Date 2021/8/21 15:05
 */

@EnableEurekaClient
@SpringBootApplication
public class EuthenticationCrowdMain {

    public static void main(String[] args) {
        SpringApplication.run(EuthenticationCrowdMain.class, args);
    }
}
