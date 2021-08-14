package com.pdsu.wl.crowd.Dao;

import com.pdsu.wl.crowd.Entity.Role;
import com.pdsu.wl.crowd.Entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByKeyword(Role role);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);

    void deleteOldRelationship(Integer adminId);

    // @Param 注解的使用场景：
    // 1.mapper中有多个参数时 2.mapper中需要给参数起别名时
    // 3.xml中参数使用$符号时需要使用@Param,默认是使用# 4.在xml中写sql语句时使用动态sql时
    void insertNewRelationship(@Param("adminId") Integer adminId,
                               @Param("roleIdList") List<Integer> roleIdList);
}