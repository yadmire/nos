package com.weiyx.nos.config.resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(ResourceConfigProperties.class)
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    ResourceConfigProperties resourceConfigProperties;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] antPatterns = (String[]) ArrayUtils.addAll(resourceConfigProperties.getExcludePriUrls(), resourceConfigProperties.getExcludePubUrls());
        http.csrf()
                .disable()
                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers(antPatterns)
                .permitAll()
                .antMatchers("/**").authenticated()
                .and().headers().cacheControl();
    }

    private TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new OauthJwtAccessTokenConverter();
        tokenConverter.setSigningKey(resourceConfigProperties.getJwtKey());
        return tokenConverter;
    }
}
