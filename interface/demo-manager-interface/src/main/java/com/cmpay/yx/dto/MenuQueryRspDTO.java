package com.cmpay.yx.dto;

import com.cmpay.framework.data.response.PageableRspDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class MenuQueryRspDTO extends PageableRspDTO {
    private List<MenuDTO> list;
}
