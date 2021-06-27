package com.weiyx.nos.iaas.authorzation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 密码加密解析器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * userDetails
     */
    @Qualifier("UserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 配置第三方客户端的名称
                .withClient("nos-api")
                // 第三方客户端的秘钥
                .secret("nos-secret")
                // 第三方客户端的授权范围
                .scopes("all")
                // 配置登陆授权服务器的方式 password , refresh_token 方式 , 就是传值的时候需要传输一个 grant_type 值的开启, 默认只有 password
                //.authorizedGrantTypes("password","refresh_token")
                // 获取的 token 有效期 半小时 (正常设置一个星期)
                .accessTokenValiditySeconds(60 * 30)
                // refresh 的 token 有效期 一天 (正常设置一个月)
                .refreshTokenValiditySeconds(24 * 60 * 60);
        super.configure(clients);
    }

    /**
     * 配置校验器
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore())
                // 添加认证管理器
                .authenticationManager(authenticationManager)
                // 添加 userDetails
                .userDetailsService(userDetailsService);
                // 设置 token 存储在哪里 , 利用 jwt 技术进行存储
                //.tokenStore(jwtTokenStore())
                // 进行密码授权 私钥加密
                //.tokenEnhancer(jwtAccessTokenConverter());
        // 调用父类的方法进行处理
        super.configure(endpoints);
    }
}
