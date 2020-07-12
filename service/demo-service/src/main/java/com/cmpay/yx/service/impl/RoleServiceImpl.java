package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.lemon.common.utils.StringUtils;
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

        RoleDO roleDO = new RoleDO();
        RoleBO bo = roleBO;
        // 这个list是用来帮上面迭代安置bean的
        List<Long> midList = roleBO.getMidList();
        // 迭代bean，把这个list插入角色菜单表，
        // 两个插入方法都是写在一个service里的，毕竟是原子操作 插入角色，他的权限也要跟着一起插入
        if(midList!=null){
                Long randomId = Long.valueOf(RandomUtils.nextInt());
                RoleMenuDO roleMenuDO = new RoleMenuDO();
                roleMenuDO.setRoleMenuId(randomId);
                roleMenuDO.setRid(roleBO.getRid());
                roleMenuDO.setMids(StringUtils.join(midList, "-"));
                roleMenuDO.setCreateTime(LocalDateTime.now());
                roleMenuDO.setCreateUserNo(roleBO.getCreateUserNo());
                roleMenuDO.setUpdateTime(LocalDateTime.now());
                roleMenuDO.setUpdateUserNo(roleBO.getUpdateUserNo());
                roleMenuDO.setIsUse(true);
            // 角色菜单表的插入
            int res2 = roleMenuDao.insertRoleMenu(roleMenuDO);
        }

        // 角色表的插入
        BeanUtils.copyProperties(roleDO,bo);
        roleDO.setCreateTime(LocalDateTime.now());
        roleDO.setCreateUserNo(1L);
        roleDO.setUpdateTime(LocalDateTime.now());
        roleDO.setUpdateUserNo(1L);

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
        RoleMenuDO roleMenuByRid = roleMenuDao.getRoleMenuByRid(rid);
        if (roleMenuByRid != null) {

        }

            String rids = roleMenuByRid.getMids();
            List<Long> midList = new ArrayList<>();
            BeanUtils.copyProperties(bo,roleDO);
            CharSequence c = "-";
            if (!"".equals(rids) && rids.contains(c)){
                String[] split = roleMenuByRid.getMids().split("-");
                for (int i = 0; i < split.length; i++) {
                    midList.add(Long.valueOf(split[i]));
                }
            }else {
                midList.add(Long.valueOf(roleMenuByRid.getMids()));
            }
            bo.setMidList(midList);


        return bo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRole(RoleBO roleBO) {
        // 先转换类型  再调用方法
        RoleDO roleDO = new RoleDO();
        RoleBO bo = roleBO;
        List<Long> midList = roleBO.getMidList();
        // 两个插入方法都是写在一个service里的，毕竟是原子操作 插入角色，他的权限也要跟着一起插入
        int res2 = 1;
        if(midList!=null) {
            RoleMenuDO roleMenuDO = new RoleMenuDO();
            roleMenuDO.setRid(roleBO.getRid());
            roleMenuDO.setMids(StringUtils.join(midList, "-"));
            roleMenuDO.setUpdateTime(LocalDateTime.now());
            roleMenuDO.setUpdateUserNo(roleBO.getUpdateUserNo());
            roleMenuDO.setIsUse(true);
            // 角色菜单表的插入
            RoleMenuDO roleMenuByRid = roleMenuDao.getRoleMenuByRid(roleBO.getRid());
            if (roleMenuByRid==null){
                roleMenuDO.setRoleMenuId(Long.valueOf(RandomUtils.nextInt()));
                res2 = roleMenuDao.insertRoleMenu(roleMenuDO);
            }else {
                res2 = roleMenuDao.updateRoleMenu(roleMenuDO);
            }
        }
        BeanUtils.copyProperties(roleDO,bo);
        roleDO.setIsUse(true);
        int res1 = roleDao.updateRole(roleDO);
        if (res1==0 || res2==0) {
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


}
