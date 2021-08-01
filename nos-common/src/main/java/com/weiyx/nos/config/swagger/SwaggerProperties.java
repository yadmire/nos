package com.weiyx.nos.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * swagger 配置文件配置值类
 *
 * @author weiyuxin
 * @date 2021/6/24
 */
@Data
@ConfigurationProperties(prefix = "swagger2")
@Component
public class SwaggerProperties {
    /**
     * 包扫描的路径
     */
    private String basePackage;

    /**
     * 联系人的名称
     */
    private String name;

    /**
     * 联系人的主页
     */
    private String url;

    /**
     * 联系人的邮箱
     */
    private String email;

    /**
     * API 的标题
     */
    private String title;

    /**
     * API 的描述
     */
    private String description;

    /**
     * API 的版本号
     */
    private String version;

}
