package com.pdsu.wl.crowd.service;

import com.pdsu.wl.crowd.Entity.Menu;

import java.util.List;

/**
 * @author wl
 * @Date 2021/8/7 9:54
 */
public interface MenuService {

    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(Integer id);
}
