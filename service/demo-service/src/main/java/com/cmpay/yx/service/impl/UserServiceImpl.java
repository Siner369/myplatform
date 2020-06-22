package com.cmpay.yx.service.impl;

import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.dao.IUserDao;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        //
        UserDO login = userDao.login();
        BeanUtils.copyProperties(login, userInfoBO);
        return userInfoBO;
    }
}
