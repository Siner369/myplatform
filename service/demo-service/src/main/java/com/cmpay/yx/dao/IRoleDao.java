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
     *新增角色
     * @param roleDO
     * @return
     */
    int insertRole(RoleDO roleDO);

    /**
     * 以UID搜索用户
     * @param rid
     * @return
     */
    RoleDO getRoleByRid(Long rid);

    /**
     * 更新用户信息
     * @param roleDO
     * @return
     */
    int updateRole(RoleDO roleDO);

    /**
     * 假删除用户
     * @param rid
     * @return
     */
    int deleteRole(Long rid);


    /**
     * 批量假删除
     * @param roleNos
     * @return
     */
    int batchDeleteRole(List<Long> roleNos);
}
