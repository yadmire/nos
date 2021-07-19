package com.weiyx.nos.service.impl;

import com.weiyx.nos.feignclient.Oauth2FeignClient;
import com.weiyx.nos.model.JwtToken;
import com.weiyx.nos.service.UserLoginService;
import com.weiyx.nos.vo.LoginDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class USerLoginServiceImpl implements UserLoginService {

    @Value("${basic.token:Basic bm9zLWFwaTpub3Mtc2VjcmV0}")
    String basicToken;

    @Autowired
    private Oauth2FeignClient oauth2FeignClient;





    @Override
    public LoginDetailVo loginByUsername(String username, String password,String loginType) {
        JwtToken jwtToken=oauth2FeignClient.getToken("password",username,password,"staff",basicToken);
        return null;
    }
}
