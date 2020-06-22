package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *
 * @author Administrator
 */
@Data
@ToString
public class UserInfoDTO {
    /**
     * @Fields username 用户名
     */
    private String username;

    /**
     * @Fields email 邮箱
     */
    private String email;
    /**
     * @Fields mobile 手机号
     */
    private String mobile;
    /**
     * @Fields state 禁用 或者 正常
     */
    private Byte state;
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
