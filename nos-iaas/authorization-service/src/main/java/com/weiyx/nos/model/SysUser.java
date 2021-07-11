package com.weiyx.nos.model;

import lombok.Data;

@Data
public class SysUser {
    private Integer id; // 用户id
    private String userName; // 用户名
    private String password; // 密码
    private String tenantCode; // 租户代码，clientId
}
