package com.weiyx.nos.config.resource;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author ：weiyuxin
 * @date ：2021/7/20
 */
public class OauthJwtAccessTokenConverter extends JwtAccessTokenConverter {
    public OauthJwtAccessTokenConverter() {
        super.setAccessTokenConverter(new OauthAccessTokenConverter());
    }
}
