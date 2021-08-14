package com.pdsu.wl.crowd.service.Impl;

import com.pdsu.wl.crowd.Dao.MenuMapper;
import com.pdsu.wl.crowd.Entity.Menu;
import com.pdsu.wl.crowd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wl
 * @Date 2021/8/7 9:54
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取全部节点
     * @return
     */
    @Override
    public List<Menu> getAll() {
        List<Menu> menuList = menuMapper.getAll();
        return menuList;
    }

    /**
     * 添加一个子节点
     * @param menu
     */
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
     * 更新节点
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 删除节点
     * @param menu
     */
    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
