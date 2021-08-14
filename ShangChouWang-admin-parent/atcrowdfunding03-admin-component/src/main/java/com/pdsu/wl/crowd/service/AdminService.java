package com.pdsu.wl.crowd.service;

import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Entity.Admin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wl
 * @Date 2021/7/17 21:23
 */
public interface AdminService {

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    PageInfo<Admin> selectAdminByKeyword(Admin admin, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

    Admin getAdminById(Integer adminId);

    void updateAdmin(Admin admin);
}
