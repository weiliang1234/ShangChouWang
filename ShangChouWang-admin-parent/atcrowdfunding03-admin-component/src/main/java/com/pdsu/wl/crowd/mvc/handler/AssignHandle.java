package com.pdsu.wl.crowd.mvc.handler;

import com.pdsu.wl.crowd.Entity.Role;
import com.pdsu.wl.crowd.service.AdminService;
import com.pdsu.wl.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wl
 * @Date 2021/8/10 17:26
 */
@Controller
public class AssignHandle {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询当前用户已获得的权限和未获得的权限
     * 方法一：自己写sql语句
     * @param adminId
     * @param modelMap
     * @return
     */
    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {

        // 1.查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        // 2.查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

        // 3.存入模型(本质上其实是：request.setAttribute("attrName",attrValue));
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";
    }

    /**
     * 查询当前用户已获得的权限和未获得的权限
     * 方法二：用所有的角色减去用户已分配的角色
     * @param adminId
     * @param modelMap
     * @return
     */
    @RequestMapping("/assign/to/assign/role/page2.html")
    public String toAssignRolePage2(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {

        // 2.查询未分配角色
        List<Role> unAssignedRoleList = new ArrayList<>();

        //
        final List<Role> roles = roleService.AllRoles();
        final List<Role> assignedRole = roleService.getAssignedRole(adminId);

        //
        for (Role role : assignedRole) {
            if (roles.contains(role)) {
                continue;
            }else {
                unAssignedRoleList.add(role);
            }
        }

        // 3.存入模型(本质上其实是：request.setAttribute("attrName",attrValue));
        modelMap.addAttribute("assignedRoleList", assignedRole);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";
    }

    /**
     * 修改当前用户的角色信息
     * @param adminId
     * @param pageNum
     * @param keyword
     * @param roleIdList
     * @return
     */
    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(@RequestParam("adminId") Integer adminId,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("keyword") String keyword,
                                            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {

        roleService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
