package com.weiyx.nos.config.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ：weiyx
 * @date ：Created in 2021/7/1 18:47
 * @description：配置redisConfig是否注入
 * @modified By：
 * @version: 1.0$
 */
public class RedisCondition implements Condition  {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        String redisNodesProperty = environment.getProperty("spring.redis.cluster.nodes");
        return StringUtils.isNotEmpty(redisNodesProperty);
    }
}
