package com.weiyx.nos.config.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth2.authorization.jwt.key-value}")
    private String JWT_KEY;
    @Value("${security.oauth2.resource.exclude-uri}")
    private String[] ANT_MATCHERS;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        System.out.printf(String.valueOf(ANT_MATCHERS.length));
        http.csrf()
                .disable()
                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers(
                        "/users/setPassword" ,
                        "/users/register",
                        "/sms/sendTo",
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/account/id/*"
                ).permitAll()
                .antMatchers("/**").authenticated()
                .and().headers().cacheControl();
    }

    private TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new OauthJwtAccessTokenConverter();
        tokenConverter.setSigningKey(JWT_KEY);
        return tokenConverter;
    }
}
