/*
 * @ClassName IUserRoleDao
 * @Description
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IUserRoleDao extends BaseDao<UserRoleDO, Long> {

    /**
     * 获取所有用户角色对象
     *
     * @return
     */
    List<UserRoleDO> getAllUserRole();

    /**
     * 按照UID RID获取用户角色对象
     *
     * @param uid
     * @return
     */
    UserRoleDO getUserRole(Long uid);

    /**
     * 批量删除跟此UID有关的所有角色信息
     *
     * @param uid
     * @return
     */
    int deleteUserRoleBatch(Long uid);

    /**
     * 增加用户角色表
     *
     * @param userRoleDO
     * @return int
     */
    int insertUserRole(UserRoleDO userRoleDO);

}
