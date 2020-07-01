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

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface IUserRoleDao extends BaseDao<UserRoleDO, Long> {


    UserRoleDO getUserRole(Long userRoleId);

    /**
     * 批量删除跟此UID有关的所有角色信息
     * @param uid
     * @return
     */
    int deleteUserRoleBatch(Long uid);

    /**
     * 批量增加用户角色表
     * @param tarList
     * @return
     */
    int insertUserRoleBatch(List<UserRoleDO> tarList);
}
