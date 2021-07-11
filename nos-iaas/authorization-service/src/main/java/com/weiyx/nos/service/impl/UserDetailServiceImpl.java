package com.weiyx.nos.service.impl;

import com.weiyx.nos.constant.LoginConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        // 如果值是空的值
        if (StringUtils.isBlank(loginType)) throw new AuthenticationServiceException("请添加 login_type 参数");
        // 创建 UserDetails 对象
        UserDetails userDetails;
        // 因为开启了 refresh_token 机制 ,在刷新 token 的时候 jwt 使用的是 username 进行查询 , 而我们将 userName 的值变成了用户的 id 所以我们需要在这里进行纠正 userName 的值变成响应的 id 值才能正常的登录
        String grant_type = requestAttributes.getRequest().getParameter("grant_type");
        // 如果 grant_type 等于 REFRESH_TOKEN 那么就进行数据的纠正 , 将 userName(id) --> userName
       // if(LoginConstant.REFRESH_TOKEN.equals(grant_type.toUpperCase())) userName = adjustUserName(userName, loginType);


        return new User(username,password,enable,accountNonExpired,credentialsNonExpired,accountNonLocked,);
    }
}
