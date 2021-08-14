package com.pdsu.wl.crowd.mvc.handler;

import com.pdsu.wl.crowd.Entity.Admin;
import com.pdsu.wl.crowd.service.AdminService;
import com.pdsu.wl.crowd.util.CrowdUtil;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wl
 * @Date 2021/7/18 20:49
 */
@Controller
public class TestHandle {

    @Autowired
    private AdminService adminService;

    final Logger logger = LoggerFactory.getLogger(TestHandle.class);

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request) {

        final boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequestType=" + judgeRequestType);
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);
       // System.out.println(10/0);
        return "target";
    }

    @RequestMapping("/send/array.one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array) {
        for (Integer number : array) {
            System.out.println("number=" + number);
        }

        return "target";
    }

    @RequestMapping("/send/array.three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array) {

        for (Integer number : array) {
            logger.info("number=" + number);
        }
        return "target";
    }

    @ResponseBody
    @RequestMapping("/send/array.two.json")
    public ResultEntity testReceiveArrayTwo(@RequestBody List<Integer> array, HttpServletRequest request) {

        final boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequestType=" + judgeRequestType);

        for (Integer number : array) {
            logger.info("number=" + number);
        }
        return ResultEntity.successWithData(array);
    }
}
