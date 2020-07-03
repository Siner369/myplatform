/*
 * @ClassName IRoleMenuDao
 * @Description
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IRoleMenuDao extends BaseDao<RoleMenuDO, Long> {

    /**
     * 查找所有角色权限菜单
     * @return
     */
    List<RoleMenuDO> selectAllRoleMenu();

    ///
    /**
     *新增权限
     * @param roleMenuDO
     * @return
     */
    int insertRoleMenu(RoleMenuDO roleMenuDO);

    /**
     * 以ROLE_MENU_ID搜索权限
     * @param roleMenuId
     * @return
     */
    RoleMenuDO getRoleMenuByRoleMenuId(Long roleMenuId);

    /**
     * 更新权限信息
     * @param roleMenuDO
     * @return
     */
    int updateRoleMenu(RoleMenuDO roleMenuDO);

    /**
     * 假删除权限,角色用
     * @param rid
     * @return
     */
    int deleteRoleMenuByRid(Long rid);

    /**
     * 假删除权限,菜单用
     * @param mid
     * @return
     */
    int deleteRoleMenuByMid(Long mid);
    ///

    /**
     * 批量插入角色菜单表
     * @param tarList
     * @return
     */
    int insertRoleMenuBatch(List<RoleMenuDO> tarList);

    /**
     * 批量删除跟此RID有关的所有菜单权限
     * @param rid
     * @return
     */
    int deleteRoleMenuBatch(Long rid);

}
