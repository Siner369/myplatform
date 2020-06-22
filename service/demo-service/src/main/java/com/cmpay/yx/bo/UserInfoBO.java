package com.cmpay.yx.bo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserInfoBO {
    /**
     * @Fields uid 用户ID
     */
    private Long uid;
    /**
     * @Fields username 用户名
     */
    private String username;
    /**
     * @Fields password 密码
     */
    private String password;
    /**
     * @Fields name 姓名
     */
    private String name;
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
