package com.cmpay.yx.service;

import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.entity.UserDO;

import java.util.List;

/**
 * @author yexing
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return List<UserDO>
     */
    List<UserDO> selectAllUser();

    /**
     * 登录方法
     * @param userInfoBO
     * @return
     */
    UserInfoBO login(UserInfoBO userInfoBO);

    /**
     * 新增用户方法
     * @param userInfoBO
     * @return
     */
    int insertUser(UserInfoBO userInfoBO);

    /**
     * 根据ID找用户
     * @param uid
     * @return
     */
    UserDO getUserByUid(Long uid);
}
