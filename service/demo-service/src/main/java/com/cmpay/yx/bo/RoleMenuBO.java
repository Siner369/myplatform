package com.cmpay.yx.bo;

import lombok.Data;
import lombok.ToString;
import java.util.List;
/**
 * @author Administrator
 */
@Data
@ToString
public class RoleMenuBO {
    /**
     * 角色ID
     */
    private Long rid;


    /**
     * 菜单ID数组
     */
    private List<Long> midList;
}
