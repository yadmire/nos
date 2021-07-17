package com.weiyx.nos.feignclient;

import com.weiyx.nos.model.JwtToken;
import com.weiyx.nos.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authorization-service")
public interface Oauth2FeignClient {
    @PostMapping("/oauth/token")
    Result<JwtToken> getToken(
            @RequestParam("grant_type") String grantType, // 授权类型
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            @RequestParam("login_type")String loginType, // 登陆类型
            @RequestHeader("Authorization") String basicToken
    );
}
