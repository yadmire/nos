package com.weiyx.nos.controller;

import com.weiyx.nos.feignclient.Oauth2FeignClient;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginController {
    @Autowired
    private Oauth2FeignClient oauth2FeignClient;


}
