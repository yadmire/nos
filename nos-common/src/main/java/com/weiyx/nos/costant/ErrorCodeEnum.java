package com.weiyx.nos.costant;

public enum ErrorCodeEnum {
    USER_UNLOGIN(401,"用户未登陆"),
    USER_NO_PERMISSION(403,"用户无权限"),
    INVALID_GRANT(402,"用户名或秘钥错误"),
    SYSTEM_ERROR(500,"系统错误");

    ;



    private int code;
    private String message;

    private ErrorCodeEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
