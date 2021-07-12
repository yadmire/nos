package com.weiyx.nos.config.feign;

import com.weiyx.nos.utils.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义远程调用OAuth2的配置项
 */
@Slf4j
public class FeinClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.info("没有请求的上下文,故无法进行token的传递");
        }
        // 进行 request 对象的获取
        HttpServletRequest request = ServletUtils.getRequest();
        // 取出 request 对象的 token 数据 , 获取我们请求上下文的头里面的AUTHORIZATION
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 如果 token 数据不是空
        if (StringUtils.isNotBlank(header)) {
            // 进行请求头 token 数据的放入
            template.header(HttpHeaders.AUTHORIZATION, header);
            log.info("本次token传递成功,token的值为:{}", header);
        }

    }
}
