package com.weiyx.nos.model;

import com.weiyx.nos.costant.ErrorCodeEnum;

/**
 * @author ：weiyuxin
 * @date ：2021/9/13
 */
public class NosException extends RuntimeException{
    private Integer errorCode;
    private String errorMessage;
    public NosException(ErrorCodeEnum errorCodeEnum){
        this.errorCode=errorCodeEnum.getCode();
        this.errorMessage=errorCodeEnum.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public NosException(String message) {
        super(message);
    }
    public NosException(String message, Throwable cause) {
        super(message, cause);
    }
}
