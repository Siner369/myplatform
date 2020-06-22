package com.cmpay.yx.auth;

import com.cmpay.lemon.framework.autoconfigure.security.SecurityProperties;
import com.cmpay.lemon.framework.security.auth.GenericAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 *
 * @author Administrator
 */
@Configuration
public class LoginConfiguration {

    @Bean
    public LoginAuthenticationProcessor matchableAuthenticationProcessor( SecurityProperties securityProperties) {
        return new LoginAuthenticationProcessor(getLoginPathPrefix(securityProperties));
    }

    private String getLoginPathPrefix(SecurityProperties securityProperties) {
        return Optional.ofNullable(securityProperties.getAuthentication()).map(a
                -> a.getLoginPathPrefix()).orElse(GenericAuthenticationFilter.DEFAULT_PREFIX_FILTER_PROCESSES_URL);
    }

}