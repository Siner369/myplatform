package com.cmpay.yx.bo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@ToString
public class UserQueryBO {

    /**
     * @Fields pageNum 第几页
     */
    private Integer pageNum;
    /**
     * @Fields pageSize 页面大小
     */
    private Integer pageSize;
    /**
     * @Fields username 用户名
     */
    private String username;
}
