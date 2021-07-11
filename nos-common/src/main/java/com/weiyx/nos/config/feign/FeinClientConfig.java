package com.weiyx.nos.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 定义远程调用OAuth2的配置项
 */
@Log4j
public class FeinClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();


    }
}
