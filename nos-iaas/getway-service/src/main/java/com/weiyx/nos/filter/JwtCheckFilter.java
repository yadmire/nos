package com.weiyx.nos.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;


@Component
public class JwtCheckFilter implements GlobalFilter, Ordered {
    /**
     * 不需要授权访问的地址字符串
     */
    @Value("${no.requires.uris:/admin/login}")
    private Set<String> noRequiresTokenUris;
    /**
     * redis 客户端对象
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {



        if (isRequireToken(exchange))
            // 放行并返回
        {
            return chain.filter(exchange);
        }
        // 获取用户的 token 数据
        String token = getUserToken(exchange);
        // 判断该用户的 token 是否有效
        if (StringUtils.isEmpty(token))
            // 如果 token 数据是空返回错误结果
        {
            return buildNoAuthorizedResult(exchange);
        }
            // 如果有这个 token 的值
        else if (stringRedisTemplate.hasKey(token))
            // 放行并返回
        {
            return chain.filter(exchange);
        }
        // 到最后都没走直接返回没有校验
        return buildNoAuthorizedResult(exchange);
    }

    private Mono<Void> buildNoAuthorizedResult(ServerWebExchange exchange) {
        return null;
    }

    private String getUserToken(ServerWebExchange exchange) {
        return null;
    }

    private boolean isRequireToken(ServerWebExchange exchange) {
        return false;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
