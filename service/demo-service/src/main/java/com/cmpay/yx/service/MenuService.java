package com.cmpay.yx.service;

import com.cmpay.yx.bo.MenuBO;
import com.cmpay.yx.bo.RoleMenuBO;
import com.cmpay.yx.entity.MenuDO;
import com.cmpay.yx.entity.RoleMenuDO;

import java.util.List;

/**
 * @author yexing
 */
public interface MenuService {
    /**
     * 查询所有菜单信息
     * @return List<UserDO>
     */
    List<MenuDO> selectAllMenu();

    /**
     *新增菜单
     * @param menuBO
     * @return
     */
    void insertMenu(MenuBO menuBO);

    /**
     * 以MID搜索菜单
     * @param mid
     * @return
     */
    MenuBO getMenuByMid(Long mid);

    /**
     * 更新菜单信息
     * @param menuBO
     * @return
     */
    void updateMenu(MenuBO menuBO);

    /**
     * 假删除菜单
     * @param mid
     * @return
     */
    void deleteMenu(Long mid);

    // 下面是角色菜单表的所有方法


    /////////////////////////////////////////

}
