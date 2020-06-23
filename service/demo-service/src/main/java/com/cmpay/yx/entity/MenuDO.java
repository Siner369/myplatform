/*
 * @ClassName MenuDO
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DataObject
public class MenuDO extends BaseDO {
    /**
     * @Fields mid 菜单ID
     */
    private Long mid;
    /**
     * @Fields menuType 菜单名称
     */
    private String menuType;
    /**
     * @Fields superMenuId 上级菜单ID 默认0
     */
    private Integer superMenuId;
    /**
     * @Fields menuName 菜单名称
     */
    private String menuName;
    /**
     * @Fields menuLink 菜单链接
     */
    private String menuLink;
    /**
     * @Fields redirect 重定向
     */
    private String redirect;
    /**
     * @Fields menuEnglishName 菜单英文名称
     */
    private String menuEnglishName;
    /**
     * @Fields authMark 授权标识
     */
    private String authMark;
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