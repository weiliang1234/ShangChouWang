package com.pdsu.wl.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wl
 * @Date 2021/8/22 14:49
 */
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 浏览器要访问的地址
        String urlPath = "/auth/member/to/reg/page.html";

        // 目标视图名称,将来要拼接前缀：prefix: classpath:/templates/,后缀suffix: .html
        String viemName = "member-reg";

        registry.addViewController(urlPath).setViewName(viemName);
        registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
    }
}
