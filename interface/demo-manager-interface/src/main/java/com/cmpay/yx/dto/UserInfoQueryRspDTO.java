package com.cmpay.yx.dto;

import com.cmpay.framework.data.response.PageableRspDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yexing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoQueryRspDTO extends PageableRspDTO {

    private List<UserInfoDTO> list;

}
