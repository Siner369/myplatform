package com.cmpay.yx.service.impl;

import com.cmpay.yx.bo.RoleMenuBO;
import com.cmpay.yx.entity.RoleMenuDO;
import com.cmpay.yx.service.RoleMenuService;

import java.util.List;

public class RoleMenuServiceImpl implements RoleMenuService {
    @Override
    public List<RoleMenuDO> selectAllRoleMenu() {
        return null;
    }

    @Override
    public void insertRoleMenu(RoleMenuBO roleMenuBO) {

    }

    @Override
    public RoleMenuBO getRoleMenuByRoleMenuId(Long roleMenuId) {
        return null;
    }

    @Override
    public void updateRoleMenu(RoleMenuBO roleMenuBO) {

    }

    @Override
    public void deleteRoleMenu(Long roleMenuId) {

    }

    @Override
    public int insertRoleMenuBatch(List<RoleMenuDO> ridList) {
        return 0;
    }

    @Override
    public int deleteRoleMenuBatch(Long rid) {
        return 0;
    }
}
