package com.pdsu.wl.crowd.service.Impl;

import com.google.common.base.Splitter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Dao.RoleMapper;
import com.pdsu.wl.crowd.Entity.Role;
import com.pdsu.wl.crowd.Entity.RoleExample;
import com.pdsu.wl.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wl
 * @Date 2021/8/1 18:45
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * Role分页查询
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Role> SelectRoleByKeyword(Role role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.selectRoleByKeyword(role);
        return new PageInfo<>(roles);
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    /**
     * 更新角色信息
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 删除角色:支持单个删除和批量删除
     * @param role
     */
    @Override
    public void deleteRole(List<Integer> ids) {

        for (Integer id : ids) {
            roleMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 查询已分配当前用户的角色
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        List<Role> roles = roleMapper.getAssignedRole(adminId);
        return roles;
    }

    /**
     * 查询未分配给当前用户的角色
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        List<Role> roles = roleMapper.getUnAssignedRole(adminId);
        return roles;
    }

    /**
     * 查询所有role
     * @return
     */
    public List<Role> AllRoles() {
        final RoleExample roleExample = new RoleExample();
        final List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    /**
     * 修改当前用户的角色信息
     * @param adminId
     * @param roleIdList
     */
    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 1.先根据当前用户的adminId删除掉他所有的旧角色信息
        roleMapper.deleteOldRelationship(adminId);

        // 2.再根据前端传过来的 roleIdList 更新当前用户的角色信息
        if (roleIdList != null && roleIdList.size() > 0) {

            // 3.roleIdList集合在sql语句中使用foreach进行遍历
            roleMapper.insertNewRelationship(adminId, roleIdList);
        }
    }
}
