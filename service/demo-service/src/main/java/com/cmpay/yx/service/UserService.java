package com.cmpay.yx.service;

import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.bo.UserRoleBO;
import com.cmpay.yx.entity.UserDO;
import com.cmpay.yx.entity.UserRoleDO;

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
     * @return int
     */
    int insertUser(UserInfoBO userInfoBO);

    /**
     * 根据ID找用户
     * @param uid
     * @return UserInfoBO
     */
    UserInfoBO getUserByUid(Long uid);

    /**
     * 更新用户信息
     * @param userInfoBO
     */
    void updateUser(UserInfoBO userInfoBO);

    /**
     * 删除用户（逻辑删除）
     * @param uid
     */
    void deleteUser(Long uid);

    /**
     * 批量删除跟此UID有关的数据
     * @param uid
     * @return int
     */
    int batchDeleteUserRole(Long uid);
}
