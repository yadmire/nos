package com.weiyx.nos.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置认证管理器
     *
     * @return 认证管理器
     * @throws Exception 异常
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置密码的验证管理器
     *
     * @return 密码的验证管理器 PasswordEncoder 对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 返回 BCrypt 加密规则方式
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置资源的放行
     *
     * @param http http 安全对象
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭 csrf 对象
        http.csrf().disable();
        // 进行资源的授权放行
        http.authorizeRequests().anyRequest().authenticated();
    }

//    /**
//     * 生成密码
//     * @param args
//     */
//    public static void main(String[] args) {
//        String ps = new BCryptPasswordEncoder().encode("123456");
//        System.out.printf(ps);
//    }
}
