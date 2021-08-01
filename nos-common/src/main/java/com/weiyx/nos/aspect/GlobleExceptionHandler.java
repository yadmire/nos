package com.weiyx.nos.aspect;

import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler  {
    @ExceptionHandler(value =RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException exception) {
        return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
