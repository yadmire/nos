package com.weiyx.nos.aspect;

import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.model.NosException;
import com.weiyx.nos.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobleExceptionHandler  {
    @ExceptionHandler(value = NosException.class)
    public Result handlerNosException(NosException exception) {
        log.error(exception.getMessage(),exception);
        return Result.fail(exception.getErrorCode(),exception.getErrorMessage());
    }
    @ExceptionHandler(value =RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException exception) {
        log.error(exception.getMessage(),exception);
        return Result.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
