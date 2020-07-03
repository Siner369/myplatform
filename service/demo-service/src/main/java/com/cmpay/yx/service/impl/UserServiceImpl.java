package com.cmpay.yx.service.impl;

import com.cmpay.lemon.common.exception.BusinessException;
import com.cmpay.lemon.framework.page.PageInfo;
import com.cmpay.lemon.framework.utils.IdGenUtils;
import com.cmpay.lemon.framework.utils.PageUtils;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.bo.UserQueryBO;
import com.cmpay.yx.bo.UserRoleBO;
import com.cmpay.yx.dao.IUserDao;
import com.cmpay.yx.dao.IUserRoleDao;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.entity.UserRoleDO;
import com.cmpay.yx.enums.MsgEnum;
import com.cmpay.yx.service.UserService;
import com.rabbitmq.http.client.domain.UserInfo;
import org.springframework.stereotype.Service;
import com.cmpay.lemon.common.utils.*;
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
public class UserServiceImpl implements UserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRoleDao userRoleDao;

    @Override
    public PageInfo<UserDO> selectAllUser(UserQueryBO queryBO) {
        UserDO userDO = new UserDO();
        UserQueryBO bo = queryBO;
        BeanUtils.copyProperties(userDO,bo);
        return PageUtils.pageQueryWithCount(queryBO.getPageNum(), queryBO.getPageSize(), ()->userDao.find(userDO));
    }

    @Override
    public UserInfoBO login(UserInfoBO userInfoBO) {
        // 创建一个DO BO用来转换上面传回来的BO
        UserDO userDO = new UserDO();
        UserInfoBO bo = new UserInfoBO();
        BeanUtils.copyProperties(userDO,userInfoBO);

        // 将装换成功的DO 去数据库查询 返回一个结果对象
        UserDO login = userDao.login(userDO);
        // 将结果对象再转成BO 送给上面
        if (login == null) {
            BusinessException.throwBusinessException(MsgEnum.FAIL);
        }
        BeanUtils.copyProperties(bo,login);
        return bo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertUser(UserInfoBO userInfoBO) {

        UserDO userDO = new UserDO();
        UserInfoBO bo = userInfoBO;

        // 这个list是用来帮上面迭代安置bean的
        List<Long> ridList = userInfoBO.getRidList();
        // 迭代bean，把这个list插入角色菜单表，
        // 两个插入方法都是写在一个service里的，毕竟是原子操作 插入用户，他的角色也要跟着一起插入
        Long urId = RandomUtils.nextLong();
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserRoleId(urId);
        userRoleDO.setUid(userInfoBO.getUid());
        // 这里我重构了rids
        String join = StringUtils.join(ridList, "-");
        userRoleDO.setRids(join);
        userRoleDO.setCreateTime(LocalDateTime.now());
        userRoleDO.setCreateUserNo(userInfoBO.getCreateUserNo());
        userRoleDO.setUpdateTime(LocalDateTime.now());
        userRoleDO.setUpdateUserNo(userInfoBO.getUpdateUserNo());
        userRoleDO.setIsUse(true);


        // 角色表的插入
        userDO.setIsUse(true);
        BeanUtils.copyProperties(userDO,bo);
        int res1 = userDao.insertUser(userDO);

        // 角色菜单表的插入
        int res2 = userRoleDao.insertUserRole(userRoleDO);
        if (res1 != 1 || res2 !=1) {
            BusinessException.throwBusinessException(MsgEnum.DB_INSERT_FAILED);
        }
        return res2;
    }

    @Override
    public UserInfoBO getUserByUid(Long uid) {
        UserInfoBO bo = new UserInfoBO();
        UserDO userDO = userDao.getUserByUid(uid);
        List<Long> ridList = new ArrayList<>();
        UserRoleDO userRole = userRoleDao.getUserRole(uid);
        if (!"".equals(userRole.getRids())){
            String[] split = userRole.getRids().split("-");
            for (int i = 0; i < split.length; i++) {
                ridList.add(Long.valueOf(split[i]));
            }
        }
        BeanUtils.copyProperties(bo,userDO);
        bo.setRidList(ridList);
        return bo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(UserInfoBO userInfoBO) {
        // 先转换类型  再调用方法
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDO,userInfoBO);
        userDO.setUpdateTime(LocalDateTime.now());
        int updateUser = userDao.updateUser(userDO);
        List<Long> ridList = userInfoBO.getRidList();
        UserRoleDO tarDO = userRoleDao.getUserRole(userInfoBO.getUid());
        tarDO.setRids(StringUtils.join(ridList,"-"));
        int updateUserRole = userRoleDao.update(tarDO);
        if (updateUserRole==0 || updateUser == 0) {
            BusinessException.throwBusinessException(MsgEnum.DB_UPDATE_FAILED);
        }
    }

    /**
     * 删除用户
     * @param uidList
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(List<Long> uidList) {
        int i = userDao.deleteUser(uidList);
        if (i != 1) {
            BusinessException.throwBusinessException(MsgEnum.DB_DELETE_FAILED);
        }
    }

    /**
     * 批量删除用户角色
     * @param uid
     * @return
     */
    @Override
    public int batchDeleteUserRole(Long uid) {
        return userRoleDao.deleteUserRoleBatch(uid);
    }


}
