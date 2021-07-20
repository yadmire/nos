package com.weiyx.nos.utils;

import com.weiyx.nos.model.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginAuthorizationUtils {
    public static LoginUser  getLoginUser(){
        Authentication authentication = getAuthentication();
        Object principal=authentication.getPrincipal();
        if(principal.getClass().getName().equals("java.lang.String")
                && StringUtils.equals("anonymousUser",(String)principal)){
            return new LoginUser(-1,"anonymousUser");
        }else if(principal.getClass().getName().equals("com.weiyx.nos.model.LoginUser")){
            return (LoginUser)principal;
        }else{
            return null;
        }
    }
    public static Authentication getAuthentication() {
        // 获取安全上下文对象
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
