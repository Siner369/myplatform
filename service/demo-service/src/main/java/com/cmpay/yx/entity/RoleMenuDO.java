/*
 * @ClassName RoleMenuDO
 * @Description 
 * @version 1.0
 * @Date 2020-06-22 23:41:43
 */
package com.cmpay.yx.entity;

import com.cmpay.lemon.framework.annotation.DataObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@DataObject
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleMenuDO extends BaseDO {
    /**
     * @Fields roleMenuId 角色菜单ID
     */
    private Long roleMenuId;
    /**
     * @Fields rid 角色ID
     */
    private Long rid;
    /**
     * @Fields mid 菜单ID
     */
    private Long mid;
    /**
     * @Fields createUserNo 创建人
     */
    private Long createUserNo;
    /**
     * @Fields createTime 创建时间
     */
    private LocalDateTime createTime;
    /**
     * @Fields updateUserNo 更新人
     */
    private Long updateUserNo;
    /**
     * @Fields updateTime 创建时间
     */
    private LocalDateTime updateTime;
    /**
     * @Fields isUse 是否可用
     */
    private Boolean isUse;

}