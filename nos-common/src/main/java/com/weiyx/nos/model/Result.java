package com.weiyx.nos.model;

import com.weiyx.nos.costant.Constants;
import com.weiyx.nos.costant.ErrorCodeEnum;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T data;
    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    /**
     * 响应错误
     *
     * @param <T> 泛型值
     * @return 统一返回值对象
     */
    public static <T> Result<T> fail() {
        return restResult(null, FAIL, null);
    }
    /**
     * 响应成功
     *
     * @param <T> 泛型值
     * @return 统一返回值对象
     */
    public static <T> Result<T> success() {
        return restResult(null, SUCCESS, null);
    }
    /**
     * 响应错误
     *
     * @param code 响应错误的状态码
     * @param msg  响应失败提示文字
     * @param <T>  泛型值
     * @return 统一返回值对象
     */
    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    /**
     * 响应错误
     *
     * @param errorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ErrorCodeEnum errorCodeEnum){
        return restResult(null, errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }
    /**
     * 响应错误
     *
     * @param data 响应失败的数据
     * @param <T>  泛型值
     * @return 统一返回值对象
     */
    public static <T> Result<T> fail(T data) {
        return restResult(data, FAIL, null);
    }
    /**
     * 构建返回值对象
     *
     * @param data 响应失败的数据
     * @param code 响应错误的状态码
     * @param msg  响应失败提示文字
     * @param <T>  泛型值
     * @return 统一返回值对象
     */
    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
