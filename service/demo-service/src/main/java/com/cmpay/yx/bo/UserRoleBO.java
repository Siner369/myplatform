package com.cmpay.yx.bo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserRoleBO {

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 角色ID数组
     */
    private List<Long> ridList;
}
