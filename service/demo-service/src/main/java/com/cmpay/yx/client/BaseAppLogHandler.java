package com.cmpay.yx.client;

import com.cmpay.framework.data.message.CmpayCmdDTO;
import com.cmpay.lemon.framework.stream.MessageHandler;
import com.cmpay.yx.bo.UserInfoBO;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class BaseAppLogHandler  implements MessageHandler<UserInfoBO, CmpayCmdDTO<UserInfoBO>> {

    @Override
    public void onMessageReceive(CmpayCmdDTO<UserInfoBO> cmdDto) {
        UserInfoBO body = cmdDto.getBody();
        System.out.println(body);
    }
}
