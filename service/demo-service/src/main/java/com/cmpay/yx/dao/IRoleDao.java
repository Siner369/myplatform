/*
 * @ClassName IRoleDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IRoleDao extends BaseDao<RoleDO, Long> {
    /**
     * 查询所有用户信息
     * @return List<UserDO>
     */
    List<RoleDO> selectAllRole();

    /**
     * 登录
     * @param roleDO
     * @return
     */
    RoleDO login(RoleDO roleDO);

    /**
     *新增用户
     * @param roleDO
     * @return
     */
    int insertUser(RoleDO roleDO);

    /**
     * 以UID搜索用户
     * @param uid
     * @return
     */
    RoleDO getUserByUid(Long uid);

    /**
     * 更新用户信息
     * @param roleDO
     * @return
     */
    int updateUser(RoleDO roleDO);

    /**
     * 假删除用户
     * @param uid
     * @return
     */
    int deleteUser(Long uid);
}