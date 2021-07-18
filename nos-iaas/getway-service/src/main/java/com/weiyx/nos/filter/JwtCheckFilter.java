package com.weiyx.nos.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;


/**
 * 401校验
 */
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


        if (isRequireToken(exchange)){
            return chain.filter(exchange); // 放行并返回
        }
        // 获取用户的 token 数据
        String token = getUserToken(exchange);
        // 判断该用户的 token 是否有效
        if (StringUtils.isEmpty(token)){
            return buildNoAuthorizedResult(exchange); // 如果 token 数据是空返回错误结果
        }
        else if (stringRedisTemplate.hasKey(token)) {
            // TODO redis 续期
            return chain.filter(exchange);  // 放行并返回
        }
        // 到最后都没走直接返回没有校验
        return buildNoAuthorizedResult(exchange);
    }

    /**
     * 返回无权限信息
     * @param exchange
     * @return
     */
    private Mono<Void> buildNoAuthorizedResult(ServerWebExchange exchange) {
        // 获取 response 对象
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应码(未授权)
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 设置响应头(响应类型)
        response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
        JSONObject json=new JSONObject();
        json.put("code",401);
        json.put("message","未登录");
        // 进行返回值的构建
        DataBuffer wrap = response.bufferFactory().wrap(json.toJSONString().getBytes());
        // 流式对象返回
        return response.writeWith(Flux.just(wrap));
    }

    /**
     * 获取 token
     * @param exchange
     * @return
     */
    private String getUserToken(ServerWebExchange exchange) {
        HttpHeaders headers=exchange.getRequest().getHeaders();
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
        return authorization == null ? null : authorization.replace("bearer ", "");
    }

    private boolean isRequireToken(ServerWebExchange exchange) {
        // TODO 校验改为数组正则判断
        // 获取请求地址
        String path = exchange.getRequest().getURI().getPath();
        // 如果集合中有值一样
        if (noRequiresTokenUris.contains(path)) {
            // 返回不需要
            return true;
        }
        // 返回需要
        return false;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
