package com.weiyx.nos.service.impl;

import com.weiyx.nos.feignclient.Oauth2FeignClient;
import com.weiyx.nos.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class USerLoginServiceImpl implements UserLoginService {
    @Autowired
    private Oauth2FeignClient oauth2FeignClient;
}
