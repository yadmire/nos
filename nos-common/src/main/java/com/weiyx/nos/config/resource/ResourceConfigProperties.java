package com.weiyx.nos.config.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix="resource")
public class ResourceConfigProperties {
    private String jwtKey;
    private String [] excludePubUrls;
    private String [] excludePriUrls;
}
