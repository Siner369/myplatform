/*
 * @ClassName IRoleMenuDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoleMenuDao extends BaseDao<RoleMenuDO, Long> {
}