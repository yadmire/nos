package com.weiyx.nos.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:
 * Servlet 客户端工具类
 *
 * @author ALIENWARE <br />
 * @date 2021/6/25
 */
public class ServletUtils {
    /**
     * 获取 request
     *
     * @return request 对象
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取 response
     *
     * @return response 对象
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取 ServletRequestAttributes 对象
     *
     * @return ServletRequestAttributes 对象
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取 RequestAttributes 对象
     *
     * @return RequestAttributes 对象
     */
    public static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }
}
