package com.cmpay.yx.service;

import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.entity.RoleDO;

import java.util.List;

/**
 * @author yexing
 */
public interface RoleService {
     /**
      * 查询所有用户信息
      * @return List<UserDO>
      */
    List<RoleDO> selectAllRole();

    /**
     *新增角色
     * @param roleBO
     * @return
     */
    void insertRole(RoleBO roleBO);

    /**
     * 以UID搜索用户
     * @param rid
     * @return
     */
    RoleBO getRoleByRid(Long rid);

    /**
     * 更新用户信息
     * @param roleBO
     * @return
     */
    void updateRole(RoleBO roleBO);

    /**
     * 假删除用户
     * @param rid
     * @return
     */
    void deleteRole(Long rid);

    /**
     * 批量删除角色
     * @param roleNos
     * @return int
     */
    int batchDeleteRole(List<Long> roleNos);
}
