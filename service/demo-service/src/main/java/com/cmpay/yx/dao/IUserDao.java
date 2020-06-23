/*
 * @ClassName IUserDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-22 10:23:41
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.bo.UserInfoBO;
import com.cmpay.yx.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IUserDao extends BaseDao<UserDO, Long> {
    /**
     * 查询所有用户信息
     * @return List<UserDO>
     */
    List<UserDO> selectAllUser();

    /**
     * 登录
     * @param userDO
     * @return
     */
    UserDO login(UserDO userDO);

    /**
     *新增用户
     * @param userDO
     * @return
     */
    int insertUser(UserDO userDO);

    /**
     * 以UID搜索用户
     * @param uid
     * @return
     */
    UserDO getUserByUid(Long uid);

    /**
     * 更新用户信息
     * @param userDO
     * @return
     */
    int updateUser(UserDO userDO);

    /**
     * 假删除用户
     * @param uid
     * @return
     */
    int deleteUser(Long uid);


}