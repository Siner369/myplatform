package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.dao.IUserDao;
import com.cmpay.yx.dao.IUserRoleDao;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.entity.UserRoleDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.UserService;
import org.springframework.stereotype.Service;
import com.cmpay.lemon.common.utils.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yexing
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRoleDao userRoleDao;

    @Override
    public List<UserDO> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public UserInfoBO login(UserInfoBO userInfoBO) {
        // 创建一个DO 用来转换上面传回来的BO
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDO,userInfoBO);

        // 将装换成功的DO 去数据库查询 返回一个结果对象
        UserDO login = userDao.login(userDO);
        // 将结果对象再转成BO 送给上面
        BeanUtils.copyProperties(userInfoBO,login);
        return userInfoBO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertUser(UserInfoBO userInfoBO) {
        UserInfoBO bo = userInfoBO;
        UserDO userDO = new UserDO();
        bo.setCreateTime(LocalDateTime.now());
        bo.setUpdateTime(LocalDateTime.now());
        bo.setState((byte) 1);
        BeanUtils.copyProperties(userDO,userInfoBO);
        int i = userDao.insertUser(userDO);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }
    }

    @Override
    public UserInfoBO getUserByUid(Long uid) {
        UserInfoBO bo = new UserInfoBO();
        UserDO userDO = userDao.getUserByUid(uid);
        BeanUtils.copyProperties(bo,userDO);
        return bo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(UserInfoBO userInfoBO) {
        // 先转换类型  再调用方法
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDO,userInfoBO);
        userDO.setUpdateTime(LocalDateTime.now());
        int i = userDao.updateUser(userDO);
        if (i==0) {
            BusinessException.throwBusinessException(MsgEnum.USER_DUPLICATE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(Long uid) {
        int i = userDao.deleteUser(uid);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_DELETE_FAILED);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertUserRole(List<UserRoleDO> ridList) {
        int i = userRoleDao.insertUserRole(ridList);
        return i;
    }
}
