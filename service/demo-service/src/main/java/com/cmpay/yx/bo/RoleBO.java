package com.cmpay.yx.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
@ToString
public class RoleBO {
    /**
     * @Fields rid 角色ID
     */
    private Long rid;
    /**
     * @Fields roleName 角色名
     */
    private String roleName;
    /**
     * @Fields roleNote 备注
     */
    private String roleNote;
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
}
