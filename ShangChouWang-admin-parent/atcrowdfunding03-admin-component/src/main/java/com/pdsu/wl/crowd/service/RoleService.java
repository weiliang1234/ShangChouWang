package com.pdsu.wl.crowd.service;

import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Entity.Role;

import java.util.List;

/**
 * @author wl
 * @Date 2021/8/1 18:45
 */
public interface RoleService {

    PageInfo<Role> SelectRoleByKeyword(Role role, Integer pageNum, Integer pageSize);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(List<Integer> ids);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);

    List<Role> AllRoles();

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);
}
