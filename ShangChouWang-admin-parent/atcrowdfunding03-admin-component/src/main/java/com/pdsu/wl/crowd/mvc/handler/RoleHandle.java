package com.pdsu.wl.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Entity.Role;
import com.pdsu.wl.crowd.service.RoleService;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wl
 * @Date 2021/8/1 18:50
 */
@Controller
public class RoleHandle {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色并分页
     * @param id
     * @param name
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity getRole(@RequestParam(value = "id") Integer id, @RequestParam("name") String name,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                ModelMap modelMap) {

        Role role = new Role(id,name);
        PageInfo<Role> result = roleService.SelectRoleByKeyword(role, pageNum, pageSize);
        return ResultEntity.successWithData(result);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity saveRole(Role role) {
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity updateRole(Role role) {
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 删除角色：单个删除和批量删除
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity deleteRole(@RequestBody List<Integer> ids) {

        roleService.deleteRole(ids);
        return ResultEntity.successWithoutData();
    }
}
