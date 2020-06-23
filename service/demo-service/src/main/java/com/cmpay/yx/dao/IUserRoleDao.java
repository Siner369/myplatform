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

    /**
     * 批量插入用户角色表
     * @param ridList
     * @return
     */
    int insertUserRole(List<UserRoleDO> ridList);

    /**
     * 批量删除跟此UID有关的所有角色信息
     * @param uid
     * @return
     */
    int deleteUserRole(Long uid);
}