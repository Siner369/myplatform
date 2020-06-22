package com.cmpay.yx.service.impl;

import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.dao.IUserDao;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.service.UserService;
import org.springframework.stereotype.Service;
import com.cmpay.lemon.common.utils.*;

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
    public int insertUser(UserInfoBO userInfoBO) {
        UserInfoBO bo = userInfoBO;
        UserDO userDO = new UserDO();
        bo.setCreateTime(LocalDateTime.now());
        bo.setUpdateTime(LocalDateTime.now());
        bo.setState((byte) 1);
        BeanUtils.copyProperties(userDO,userInfoBO);
        int i = userDao.insertUser(userDO);
        return i;
    }

    @Override
    public UserDO getUserByUid(Long uid) {
        return null;
    }
}
