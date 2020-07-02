package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.bo.RoleMenuBO;
import com.cmpay.yx.dao.IRoleDao;
import com.cmpay.yx.dao.IRoleMenuDao;
import com.cmpay.yx.entity.RoleDO;
import com.cmpay.yx.entity.RoleMenuDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.RoleService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yexing
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private IRoleDao roleDao;

    @Resource
    private IRoleMenuDao roleMenuDao;

    @Override
    public List<RoleDO> selectAllRole() {
        return roleDao.selectAllRole();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertRole(RoleBO roleBO) {
        Long randomId = RandomUtils.nextLong();
        RoleDO roleDO = new RoleDO();
        RoleBO bo = roleBO;
        List<RoleMenuDO> tarList = new ArrayList<>();

        // 这个list是用来帮上面迭代安置bean的
        List<Long> midList = roleBO.getMidList();
        // 迭代bean，把这个list插入角色菜单表，
        // 两个插入方法都是写在一个service里的，毕竟是原子操作 插入角色，他的权限也要跟着一起插入
        if(midList!=null){
            for (int i = 0; i < roleBO.getMidList().size(); i++) {
                RoleMenuDO roleMenuDO = new RoleMenuDO();
                roleMenuDO.setRoleMenuId(randomId);
                roleMenuDO.setRid(roleMenuDO.getRid());
                roleMenuDO.setMid(midList.get(i));
                roleMenuDO.setCreateTime(LocalDateTime.now());
                roleMenuDO.setCreateUserNo(roleBO.getCreateUserNo());
                roleMenuDO.setUpdateTime(LocalDateTime.now());
                roleMenuDO.setUpdateUserNo(roleBO.getUpdateUserNo());
                roleMenuDO.setIsUse(true);

                tarList.add(roleMenuDO);
            }
            // 角色菜单表的插入
            int res2 = roleMenuDao.insertRoleMenuBatch(tarList);

        }

        // 角色表的插入
        BeanUtils.copyProperties(roleDO,bo);
        int res1 = roleDao.insertRole(roleDO);
        if (res1 != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }

        return res1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RoleBO getRoleByRid(Long rid) {
        RoleBO bo = new RoleBO();
        RoleDO roleDO = roleDao.getRoleByRid(rid);
        BeanUtils.copyProperties(bo,roleDO);
        return bo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRole(RoleBO roleBO) {
        // 先转换类型  再调用方法
        RoleDO roleDO = new RoleDO();
        RoleBO bo = roleBO;

        // 这个list是用来批量插入角色菜单权限
        List<Long> midList = roleBO.getMidList();
        List<RoleMenuDO> tarList = new ArrayList<>();
        // 迭代bean，把这个list插入角色菜单表，
        // 两个插入方法都是写在一个service里的，毕竟是原子操作 插入角色，他的权限也要跟着一起插入
        if(midList!=null){
            for (int i = 0; i < roleBO.getMidList().size(); i++) {
                RoleMenuDO roleMenuDO = new RoleMenuDO();
                roleMenuDO.setRid(roleMenuDO.getRid());
                roleMenuDO.setMid(midList.get(i));
                roleMenuDO.setUpdateTime(LocalDateTime.now());
                roleMenuDO.setUpdateUserNo(roleBO.getUpdateUserNo());

                tarList.add(roleMenuDO);
            }
            // 角色菜单表的插入
            int res2 = roleMenuDao.insertRoleMenuBatch(tarList);

        }
        BeanUtils.copyProperties(roleDO,bo);
        int res1 = roleDao.updateRole(roleDO);
        if (res1==0) {
            BusinessException.throwBusinessException(MsgEnum.USER_DUPLICATE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteRole(Long rid) {
        int i = roleDao.deleteRole(rid);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_DELETE_FAILED);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int batchDeleteRole(List<Long> roleNos) {
        return roleDao.batchDeleteRole(roleNos);
    }


    // 下面的是菜单角色表的实现方法

    @Override
    public List<RoleMenuDO> selectAllRoleMenu() {
        return roleMenuDao.selectAllRoleMenu();
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
