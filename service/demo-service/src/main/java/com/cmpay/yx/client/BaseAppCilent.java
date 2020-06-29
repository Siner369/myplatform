package com.cmpay.yx.client;

import com.cmpay.lemon.framework.stream.Source;
import com.cmpay.lemon.framework.stream.StreamClient;
import com.cmpay.yx.bo.UserInfoBO;

/**
 * @author Administrator
 */
@StreamClient("buidemoa")
public interface BaseAppCilent {

    /**
     * 异步测试
     *
     * @param userInfoBO
     */
    @Source(handlerBeanName = "baseAppLogHandler", group = "buidemoa", prefix = "mirror.")
    void test(UserInfoBO userInfoBO);

}
