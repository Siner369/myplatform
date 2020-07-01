package com.cmpay.yx.dto;

import com.cmpay.framework.data.response.PageableRspDTO;
import lombok.Data;
import lombok.ToString;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserInfoQueryReqDTO extends PageableRspDTO {

    private String userName;
}
