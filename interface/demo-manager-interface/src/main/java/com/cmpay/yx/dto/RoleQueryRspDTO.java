package com.cmpay.yx.dto;


import com.cmpay.framework.data.response.PageableRspDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQueryRspDTO extends PageableRspDTO {

    private List<RoleDTO> list;

}
