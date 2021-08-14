package com.pdsu.wl.crowd;

import com.pdsu.wl.crowd.Dao.AdminMapper;
import com.pdsu.wl.crowd.Entity.Admin;
import com.pdsu.wl.crowd.Entity.Menu;
import com.pdsu.wl.crowd.service.AdminService;
import com.pdsu.wl.crowd.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wl
 * @Date 2021/7/16 18:00
 */

// 在类上标记必要的注解,Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestDataSource {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MenuService menuService;

    @Test
    public void testConnection() throws Exception {
        final Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsert() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Admin admin = new Admin(2, "wl", "123456", "魏亮", "2663107564@qq.com", df.format(new Date()));
        adminMapper.insert(admin);
    }

    @Test
    public void testTx() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Admin admin = new Admin(6, "Tom6", "123456", "Tom", "2663107564@qq.com", df.format(new Date()));
        adminService.saveAdmin(admin);
    }

    @Test
    public void testselect() {
        final Admin admin = adminService.getAdminByLoginAcct("wl", "123456");
        System.out.println(admin);
    }

    @Test
    public void testGetAllTree() {
        final List<Menu> menuList = menuService.getAll();
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
    }
}
