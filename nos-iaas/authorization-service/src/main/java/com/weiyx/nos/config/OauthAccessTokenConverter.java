package com.weiyx.nos.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

/**
 * @author ：weiyuxin
 * @date ：2021/7/20
 */
public class OauthAccessTokenConverter extends DefaultAccessTokenConverter {
    public OauthAccessTokenConverter(){
        OauthUserAuthenticationConverter converter = new OauthUserAuthenticationConverter();
        super.setUserTokenConverter(converter);
    }

}
