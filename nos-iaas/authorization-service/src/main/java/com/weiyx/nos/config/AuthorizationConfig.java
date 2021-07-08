package com.weiyx.nos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${jwt.key}")
    private String jwtKey;

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
    @Qualifier("userDetailServiceImpl")
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
                .authorizedGrantTypes("password","refresh_token")
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
                .userDetailsService(userDetailsService)
                // 设置 token 存储在哪里 , 利用 jwt 技术进行存储
                .tokenStore(jwtTokenStore())
                // 进行密码授权 私钥加密
                .tokenEnhancer(jwtAccessTokenConverter());
        // 调用父类的方法进行处理
        super.configure(endpoints);
    }

    /**
     * 创建 jwtToken 存储对象
     *
     * @return jwt token 存储对象
     */
    private TokenStore jwtTokenStore() {
        // 创建 JwtToken 存储对象 并返回
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    /**
     * 设置 token 加密解密规则
     * 因为之前的 redis 存储的方式虽然解决了多个授权服务器获取 token 信息的共享机制, 但是又再次出现了一个问题, 那就是存储的时候所有的资源服务器都访问一个授权服务器的时候会出现授权服务器压力非常大的原因, 所以使用 JWT 技术进行 user 的数据加密存储在 Token 中从根本问题上解决了授权服务器压力大的原因
     * 因为 user 数据存储在 token 中 使用密钥对进行加密 , 所以就不需要使用 redis 进行数据的存储了, 即解决了多个授权服务器数据共享的问题 , 也解决了授权服务器压力大的原因
     * 所以在当前的配置文件中删除的 redis 的相关配置代码
     *
     * @return JwtAccessTokenConverter token 加密规则对象
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // 创建 jwt 验证规则对象
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 读取生成的私钥文件
       // ClassPathResource classPathResource = new ClassPathResource("coinexchange.jks");
        // 创建秘钥存储对象
        //KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "coinexchange".toCharArray());
       // KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory();
        // 给创建的 jwtAccessTokenConverter 设置秘钥对
        //jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("coinexchange", "coinexchange".toCharArray()));

        //使用对称加密
        jwtAccessTokenConverter.setSigningKey(jwtKey);

        // 返回创建的 jwtAccessTokenConverter 对象
        return jwtAccessTokenConverter;
    }

}
