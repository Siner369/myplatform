/*
 * @ClassName IMenuDao
 * @Description 
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.dao;

import com.cmpay.lemon.framework.dao.BaseDao;
import com.cmpay.yx.entity.MenuDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface IMenuDao extends BaseDao<MenuDO, Long> {
}