package com.weiyx.nos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${security.oauth2.authorization.jwt.key-value}")
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

    @Resource
    private UserJWTTokenEnhancer userJwtTokenEnhancer;

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
                .secret(passwordEncoder.encode("nos-secret"))
                // 第三方客户端的授权范围
                .scopes("all")
                // 配置登陆授权服务器的方式 password , refresh_token 方式 , 就是传值的时候需要传输一个 grant_type 值的开启, 默认只有 password
                .authorizedGrantTypes("password","refresh_token")
                // 获取的 token 有效期 半小时 (正常设置一个星期)
                .accessTokenValiditySeconds(60 * 30)
                // refresh 的 token 有效期 一天 (正常设置一个月)
                .refreshTokenValiditySeconds(24 * 60 * 60)
                .and()

                .withClient("nos-web")
                .secret("nos-web-secret")
                .scopes("all")
                // 配置登陆授权服务器的方式 client_credentials 临时获取 token 的方式
                .authorizedGrantTypes("client_credentials")
                // 设置 token 的生效时间为 10 分钟
                .accessTokenValiditySeconds(60 * 10);
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
                .tokenEnhancer(initTokenChain());
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
     * 创建token链
     *
     * @return
     */
    private TokenEnhancerChain initTokenChain(){
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        List<TokenEnhancer> list=new ArrayList<>();
        list.add(userJwtTokenEnhancer);          //添加自定义tokenEnhancer
        list.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(list);
        return tokenEnhancerChain;
    }
    /**
     * 设置 token 加密解密规则
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
