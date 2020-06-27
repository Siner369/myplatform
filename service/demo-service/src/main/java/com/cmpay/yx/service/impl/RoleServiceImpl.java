package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.common.utils.BeanUtils;
import com.cmpay.yx.bo.RoleBO;
import com.cmpay.yx.dao.IRoleDao;
import com.cmpay.yx.entity.RoleDO;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yexing
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public List<RoleDO> selectAllRole() {
        return roleDao.selectAllRole();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertRole(RoleBO roleBO) {
        RoleDO roleDO = new RoleDO();
        RoleBO bo = roleBO;
        BeanUtils.copyProperties(roleDO,bo);
        roleDO.setCreateTime(LocalDateTime.now());
        roleDO.setUpdateTime(LocalDateTime.now());
        int i = roleDao.insertRole(roleDO);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }
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
        BeanUtils.copyProperties(roleDO,roleBO);
        roleDO.setUpdateTime(LocalDateTime.now());
        int i = roleDao.updateRole(roleDO);
        if (i==0) {
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
}
