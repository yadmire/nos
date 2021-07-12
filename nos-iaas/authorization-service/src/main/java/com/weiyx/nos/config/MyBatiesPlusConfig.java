package com.weiyx.nos.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.weiyx.nos.mapper.iaas")
public class MyBatiesPlusConfig {
}
