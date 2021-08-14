package com.pdsu.wl.crowd.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pdsu.wl.crowd.Dao.AdminMapper;
import com.pdsu.wl.crowd.Entity.Admin;
import com.pdsu.wl.crowd.Entity.AdminExample;
import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.exception.LoginAccAlreadyInUseException;
import com.pdsu.wl.crowd.exception.LoginAccAlreadyInUseForUpdataException;
import com.pdsu.wl.crowd.exception.LoginFailedException;
import com.pdsu.wl.crowd.service.AdminService;
import com.pdsu.wl.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author wl
 * @Date 2021/7/17 21:25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录验证
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        // 1.根据登录账号查询Admin对象
        // ①创建AdminExample对象
        AdminExample adminExample = new AdminExample();

        // ②创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        // ③在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        final List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断是否查询到Admin对象
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        final Admin admin = admins.get(0);

        // 3.如果admin为null抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.如果admin不为null,则从admin中取出密码
        final String pswd = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        final String md5 = CrowdUtil.MD5(userPswd);

        // 6.对密码进行比较
        if (!Objects.equals(pswd, md5)) {
            // 7.如果密码不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 8.如果一致就返回Admin
        return admin;
    }

    /**
     * 添加 admin
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {

        // 1.密码加密
        String userPswd = admin.getUserPswd();
        userPswd = CrowdUtil.MD5(userPswd);
        admin.setUserPswd(userPswd);


        // 2.生成创建时间
        Date date = new Date();
        // 规定时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将我们需要的时间格式化为我们规定的时间格式
        String createTime = simpleDateFormat.format(date);
        admin.setCreateTime(createTime);

        // 3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
           // logger.info
            if (e instanceof DuplicateKeyException) {
                throw new LoginAccAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public PageInfo<Admin> selectAdminByKeyword(Admin admin, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(admin);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    /**
     * 单个删除 admin
     * @param adminId
     */
    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    /**
     * 根据Id查询admin
     * @param adminId
     * @return
     */
    @Override
    public Admin getAdminById(Integer adminId) {

        final Admin admin = adminMapper.selectByPrimaryKey(adminId);

        return admin;
    }

    /**
     * 更新admin
     * @param admin
     */
    public void updateAdmin(Admin admin) {
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();

            // 如果更新的账号已经存在,抛出异常
            if (e instanceof DuplicateKeyException) {
                throw new LoginAccAlreadyInUseForUpdataException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }
    }
}
