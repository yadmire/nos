package com.weiyx.nos.config.resource;

import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

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
