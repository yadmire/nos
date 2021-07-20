package com.weiyx.nos.service.impl;

import com.weiyx.nos.constant.LoginConstant;
import com.weiyx.nos.model.NosUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        // 如果值是空的值
        if (StringUtils.isBlank(loginType)){
            throw new AuthenticationServiceException("请添加 login_type 参数");
        }
        // 创建 UserDetails 对象
        UserDetails userDetails;
        // 因为开启了 refresh_token 机制 ,在刷新 token 的时候 jwt 使用的是 username 进行查询 , 而我们将 userName 的值变成了用户的 id 所以我们需要在这里进行纠正 userName 的值变成响应的 id 值才能正常的登录
        String grant_type = requestAttributes.getRequest().getParameter("grant_type");
        // 如果 grant_type 等于 REFRESH_TOKEN 那么就进行数据的纠正 , 将 userName(id) --> userName
        if(LoginConstant.REFRESH_TOKEN.equals(grant_type.toUpperCase())){
            // refresh token 的用户名为用户id，需要进行转转
        }
        switch (loginType){
            case "cus":{
                userDetails = getCusUserDetails(username);
                break;
            }
            case "staff":{
                userDetails = getStaffUserDetails(username);
                break;
            }
            default:{
                throw new AuthenticationServiceException("非法的 login_type login_type:"+loginType);
            }
        }
        return userDetails;
    }
    private UserDetails getStaffUserDetails(String username) {
        UserDetails userDetails=null;
        userDetails=jdbcTemplate.queryForObject(LoginConstant.QUERY_ADMIN_SQL,(resultSet, i) -> {
            if(resultSet.wasNull()){
                throw new UsernameNotFoundException("用户:" + username + "不存在");
            }
            int userId = resultSet.getInt("id");
            // 拿出用户的密码
            String password = resultSet.getString("password");
            // 拿出用户的状态
            int status = resultSet.getInt("status");
            List<String> permissions=jdbcTemplate.queryForList(LoginConstant.QUERY_AUTHORITYBYUSER,String.class,userId);
            // 创建一个用户对象 , 这里的用户名称推荐写 id 以后在拿出对象的时候查询本条数据就会方便许多, 不会出现重名的用户拿不到的现象
            return  new NosUser(userId,username, password,
                    permissions.stream().distinct().map(auCode -> new SimpleGrantedAuthority(auCode)).collect(Collectors.toList()));
//            return  new User(username, password,
//                    permissions.stream().distinct().map(auCode -> new SimpleGrantedAuthority(auCode)).collect(Collectors.toList()));
        },username);
        return userDetails;
    }

    private UserDetails getCusUserDetails(String username) {
        UserDetails userDetails=null;
        userDetails=jdbcTemplate.queryForObject(LoginConstant.QUERY_CUS_SQL,(resultSet, i) -> {
            if(resultSet.wasNull()){
                throw new UsernameNotFoundException("用户:" + username + "不存在");
            }
            long userId = resultSet.getLong("id");
            // 拿出用户的密码
            String password = resultSet.getString("password");
            // 拿出用户的状态
            int status = resultSet.getInt("status");
            List<String> permissions=jdbcTemplate.queryForList(LoginConstant.QUERY_AUTHORITYBYUSER,String.class,userId);
            // 创建一个用户对象 , 这里的用户名称推荐写 id 以后在拿出对象的时候查询本条数据就会方便许多, 不会出现重名的用户拿不到的现象
            return new User(String.valueOf(userId), password, status == 1, true, true, true,
                    permissions.stream().distinct().map(auCode -> new SimpleGrantedAuthority(auCode)).collect(Collectors.toList()));
        },username);
        return userDetails;

    }
}
