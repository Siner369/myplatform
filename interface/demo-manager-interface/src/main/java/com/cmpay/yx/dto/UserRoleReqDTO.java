package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserRoleReqDTO {

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 角色ID数组
     */
    private List<Long> ridList;
}
