package com.weiyx.nos.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

/**
 * @author ：weiyx
 * @date ：Created in 2021/7/1 16:51
 * @description：网关测试类
 * @modified By：
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    ValueOperations<String, String> stringRedis;

    ListOperations<String, String> listRedis;

    /**
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，
     * 类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后运行。
     */
    @PostConstruct
    public void init() {
        stringRedis = stringRedisTemplate.opsForValue();
        listRedis = redisTemplate.opsForList();
    }
    @Test
    public void testPutString() {
        stringRedis.set("str:name", "CUIT");
    }
    @Test
    public void testGetString() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + stringRedis.get("str:name"));
    }

}
