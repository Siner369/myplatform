package com.cmpay.yx.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserDeleteReqDTO {
    private Long[] userIds;
}
