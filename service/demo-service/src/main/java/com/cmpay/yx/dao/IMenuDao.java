/*
 * @ClassName IMenuDao
 * @Description
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.MenuDO;
import com.cmpay.yx.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.awt.*;
import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IMenuDao extends BaseDao<MenuDO, Long> {
    /**
     * 查询所有菜单信息
     * @return List<UserDO>
     */
    List<MenuDO> selectAllMenu();

    /**
     *新增菜单
     * @param menuDO
     * @return
     */
    int insertMenu(MenuDO menuDO);

    /**
     * 以MID搜索菜单
     * @param mid
     * @return
     */
    MenuDO getMenuByMid(Long mid);

    /**
     * 更新菜单信息
     * @param menuDO
     * @return
     */
    int updateMenu(MenuDO menuDO);

    /**
     * 假删除菜单
     * @param mid
     * @return
     */
    int deleteMenu(Long mid);

}
