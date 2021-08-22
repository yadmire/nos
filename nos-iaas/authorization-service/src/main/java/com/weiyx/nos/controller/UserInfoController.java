package com.weiyx.nos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @GetMapping("/user/info")
    public Principal userInfo(Principal principal){
        Authentication authentication = getAuthentication();
        return principal;
    }
    public static Authentication getAuthentication() {
        // 获取安全上下文对象
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
