package com.pdsu.wl.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wl
 * @Date 2021/8/14 21:56
 */
@EnableEurekaClient
@SpringBootApplication
//@MapperScan("com.pdsu.wl.crowd.mapper")
public class MysqlCrowdMain {
    public static void main(String[] args) {
        SpringApplication.run(MysqlCrowdMain.class, args);
    }
}
