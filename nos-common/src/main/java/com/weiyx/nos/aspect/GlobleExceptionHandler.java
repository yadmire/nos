package com.weiyx.nos.aspect;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler  {
    @ExceptionHandler(value = ApiException.class)
    public Result handlerApiException(ApiException exception) {
        IErrorCode errorCode = exception.getErrorCode();
        if (errorCode != null) {
            return Result.fail(errorCode);
        }
        return Result.fail(exception.getMessage());
    }
    @ExceptionHandler(value =RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException exception) {
        return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }
    @ExceptionHandler(value =Exception.class)
    public Result handlerException(Exception exception) {
        return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
