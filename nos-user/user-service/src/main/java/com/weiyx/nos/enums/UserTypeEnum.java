package com.weiyx.nos.enums;

/**
 * @author ：weiyuxin
 * @date ：2021/7/21
 */
public enum UserTypeEnum {
    SYSTEM("admin","管理员"),
    CUSTOMER("cus","客户");

    private String code;
    private String description;
    private UserTypeEnum(String code,String description){
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
