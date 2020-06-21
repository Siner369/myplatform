package com.cmpay.yx;

import com.cmpay.lemon.common.LemonFramework;
import com.cmpay.lemon.framework.LemonBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;


/**
 * @author Administrator
 */
@LemonBootApplication(exclude = {RabbitAutoConfiguration.class}, value = {"com.cmpay.yx"})
public class YxApplication {
    public static void main(String[] args) {
        LemonFramework.run(YxApplication.class, args);
    }
}