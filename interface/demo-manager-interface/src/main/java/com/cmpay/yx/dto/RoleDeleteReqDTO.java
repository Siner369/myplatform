package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Administrator
 */
@Data
@ToString
public class RoleDeleteReqDTO {
    private Long[] roleIds;
}
