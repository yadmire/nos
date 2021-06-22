package com.weiyx.nos.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class,args);
    }
}
