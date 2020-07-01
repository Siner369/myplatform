package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class MenuDTO {
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
     * @Fields child 子菜单
     */
    private List<MenuDTO> children;
}
