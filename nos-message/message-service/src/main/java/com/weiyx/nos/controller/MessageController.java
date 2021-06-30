package com.weiyx.nos.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "消息服务")
public class MessageController {
    @Autowired
    private RedisTemplate<String, Object> template;
    @GetMapping("/test")
    public String  testMessage(@RequestParam(value = "key")String key){
        return  template.opsForValue().get(key).toString();
    }
    @GetMapping("/settest")
    public String  testSetMessage(@RequestParam(value = "key")String key,@RequestParam(value = "val")String value){
        template.opsForValue().set(key,value);
        return  "ok";
    }
}
