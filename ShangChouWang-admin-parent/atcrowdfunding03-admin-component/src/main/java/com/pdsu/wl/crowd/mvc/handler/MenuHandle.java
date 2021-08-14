package com.pdsu.wl.crowd.mvc.handler;

import com.pdsu.wl.crowd.Entity.Menu;
import com.pdsu.wl.crowd.service.MenuService;
import com.pdsu.wl.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wl
 * @Date 2021/8/7 9:57
 */
@Controller
public class MenuHandle {

    @Autowired
    private MenuService menuService;

    /**
     * 获取全部菜单信息,并生成树形结构(方法一)
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/get/whole/treeold")
    public ResultEntity<Menu> getWholeTreeOld() {

        // 1.查询全部的Menu对象
        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量来存储找到的根节点
        Menu root = null;

        // 3.遍历menuList
        for (Menu menu : menuList) {

            // 4.获取当前menu对象的pid属性值
            Integer pid = menu.getPid();

            // 5.检查pid是否为null
            if (pid == null) {

                // 6.把当前正在遍历的这个menu对象赋值给root
                root = menu;

                // 7.停止本次循环,继续执行下一次循环
                continue;
            }

            // 8.如果pid不为null,说明当前节点有父节点就可以进行组装,建立父子关系
            for (Menu Father : menuList) {

                // 9.获取father的id属性
                final Integer id = Father.getId();

                // 10.寻找子节点的父节点,每个子节点都有一个父节点
                if (Objects.equals(id, pid)) {

                    // 11.将子节点添加到父节点的Children集合中,Children被定义为一个list集合
                    Father.getChildren().add(menu);

                    // 12.找到这个子节点的父节点后,就结束此循环,再从下一个子节点开始,寻找它的父节点
                    break;
                }

            }
        }

        // 组装后返回
        return ResultEntity.successWithData(root);
    }

    /**
     * 获取全部菜单信息,并生成树形结构(方法二)：时间效率高
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTreeNew() {

        // 1.查询全部的Menu对象
        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量来存储找到的根节点
        Menu root = null;

        // 3.创建Map对象用来存储id和Menu对象的对应关系便于查找父节点
        Map<Integer, Menu> menuMap = new HashMap<>();

        // 4.遍历menuList填充menuMap
        for (Menu menu : menuList) {

            Integer id = menu.getId();

            menuMap.put(id, menu);
        }

        // 5.再次遍历menuList查找根节点、组装父子节点
        for (Menu menu : menuList) {

            // 6.获取当前menu对象的pid属性值
            final Integer pid = menu.getPid();

            // 7.如果pid为null,判定为根节点
            if (pid == null) {
                root = menu;

                // 8.如果当前节点是根节点,那么肯定没有父节点,不必继续执行
                continue;
            }

            // 9.如果pid不为null,说明当前节点有父节点,那么可以根据pid到menuMap中查找对应的Menu对象
            final Menu father = menuMap.get(pid);

            // 10.将当前节点存入父节点的children集合
            father.getChildren().add(menu);
        }

        // 11.经过上面的运算,根节点包含了整个树形结构,返回根节点就是返回整个书
        return ResultEntity.successWithData(root);
    }

    /**
     * 添加一个子节点
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu) {
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }

    /**
     * 更新节点
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/update.json")
    public ResultEntity<String> updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }

    /**
     * 删除节点
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/remove.json")
    public ResultEntity<String> deleteMenu(@RequestParam("id") Integer menuId) {
        menuService.deleteMenu(menuId);
        return ResultEntity.successWithoutData();
    }
}
