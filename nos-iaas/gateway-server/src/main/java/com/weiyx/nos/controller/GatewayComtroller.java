package com.weiyx.nos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：weiyuxin
 * @date ：2021/7/29
 */
@RestController
public class GatewayComtroller {
    @GetMapping("/gateway/test")
    public String test(){
        return "test";
    }
}
