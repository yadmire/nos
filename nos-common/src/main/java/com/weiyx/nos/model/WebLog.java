package com.weiyx.nos.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * web 日志对象
 * @author ：weiyuxin
 * @date ：2021/7/21
 */
@Data
@EqualsAndHashCode
public class WebLog {
    private String description;
    private String username;
    private Integer id;
    private Integer spendTime;
    private String basePath;
    private String uri;
    private String url;
    private String method;
    private String ip;
    private Object parameter;
    private Object result;

}
