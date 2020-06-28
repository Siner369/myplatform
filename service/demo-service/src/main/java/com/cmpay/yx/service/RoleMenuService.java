package com.cmpay.yx.service;

import com.cmpay.yx.bo.RoleMenuBO;
import com.cmpay.yx.entity.RoleMenuDO;

import java.util.List;

/**
 * @author Administrator
 */
public interface RoleMenuService {
    /**
     * 查找所有角色权限菜单
     * @return
     */
    List<RoleMenuDO> selectAllRoleMenu();

    ///
    /**
     *新增权限
     * @param roleMenuBO
     * @return
     */
    void insertRoleMenu(RoleMenuBO roleMenuBO);

    /**
     * 以ROLE_MENU_ID搜索权限
     * @param roleMenuId
     * @return
     */
    RoleMenuBO getRoleMenuByRoleMenuId(Long roleMenuId);

    /**
     * 更新权限信息
     * @param roleMenuBO
     * @return
     */
    void updateRoleMenu(RoleMenuBO roleMenuBO);

    /**
     * 假删除权限
     * @param roleMenuId
     * @return
     */
    void deleteRoleMenu(Long roleMenuId);
    ///

    /**
     * 批量插入角色菜单表
     * @param ridList
     * @return
     */
    int insertRoleMenuBatch(List<RoleMenuDO> ridList);

    /**
     * 批量删除跟此RID有关的所有菜单权限
     * @param rid
     * @return
     */
    int deleteRoleMenuBatch(Long rid);

}
