package com.pdsu.wl.crowd.Handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wl
 * @Date 2021/8/21 15:17
 */
@Controller
public class PortalHandle {

    @RequestMapping("/")
    public String showPortalPage() {

        // 加载数据

        return "portal";
    }
}
