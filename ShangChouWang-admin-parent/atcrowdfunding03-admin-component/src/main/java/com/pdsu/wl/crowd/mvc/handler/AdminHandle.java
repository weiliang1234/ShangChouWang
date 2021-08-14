package com.pdsu.wl.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Entity.Admin;
import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.exception.LoginFailedException;
import com.pdsu.wl.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author wl
 * @Date 2021/7/22 10:48
 */
@Controller
public class AdminHandle {

    @Autowired
    private AdminService adminService;

    /**
     * 登录检查
     * @param loginAcct
     * @param userPswd
     * @param session
     * @return
     */
    @RequestMapping("/security/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session) {

        // 调用Service方法执行登录检查
        // 这个方法如果能够返回admin对象说明登录成功,如果账号、密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        // 将登录成功返回的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        // 防止刷新时重复提交表达,进行重定向
        return "redirect:/admin/to/main/page.html";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/security/do/logout.html")
    public String dologout(HttpSession session) {
        // 销毁session
        session.invalidate();
        // 重新跳转到登录页面
        return "admin-login";
    }

    /**
     * 分页
     * @param keyword 使用@RequestParam注解的defaultValue属性,指定默认值,在请求中没有携带对应参数时使用默认值
     *                keyword默认值使用空字符串,和SQL语句配合实现两种情况适配
     * @param pageNum 表示第几页,默认是第 1 页
     * @param pageSize  页面的大小,默认值是 5
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(Admin admin,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                              ModelMap modelMap) {

        // 调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.selectAdminByKeyword(admin, pageNum, pageSize);

        // 将PageInfo对象存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }

    /**
     * 删除admin
     * @param adminId
     * @return
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId") Integer adminId,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") Integer keyword) {

        adminService.remove(adminId);
        // 重定向到 /admin/get/page.html
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    /**
     * 添加一个admin
     * @param admin
     * @return
     */
    @RequestMapping("/admin/save.html")
    public String save(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE; // 直接返回最后一页,使我们能直接看到新增的数据
    }

    /**
     * 先根据Id查询要修改的Admin,在页面进行回显
     * @param adminId
     * @param modelMap
     * @return
     */
    @RequestMapping("admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {

        // 1.根据adminId查询Admin对象
        Admin admin = adminService.getAdminById(adminId);

        // 2.将Admin对象存入模型
        modelMap.addAttribute("admin", admin);

        return "admin-edit";
    }

    /**
     * 更新admin
     * @param admin
     * @param pageNum
     * @param keyword
     * @return
     */
    @RequestMapping("admin/update.html")
    public String EditAdmin(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword) {

        adminService.updateAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
}
