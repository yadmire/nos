package com.weiyx.nos.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.weiyx.nos.model.WebLog;
import com.weiyx.nos.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：weiyuxin
 * @date ：2021/7/21
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(* com.weiyx.nos.controller.*.*(..))")
    public void webLog(){}
    /**
     * 2 记录日志的环绕通知
     */
    @Around("webLog()")
    public Object recodeWebLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 创建返回的对象, 因为不能确定方法执行结束之后返回的对象是什么, 所以定义返回 object
        Object result = null;
        // 创建 webLog 对象
        WebLog webLog = new WebLog();
        // 获取当前时间
        long start = System.currentTimeMillis();
        // 执行方法的真实调用
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        // 获取结束方法的当前时间
        long end = System.currentTimeMillis();
        // 设置执行方法的调用的时间
        webLog.setSpendTime((int) (start - end) / 1000);
        // 获取当前请求的实体对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取当前请求的 request 对象
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取安全的上下文
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取请求的 url 地址
        String url = request.getRequestURL().toString();
        // 设置请求的uri
        webLog.setUri(request.getRequestURI());
        // 设置请求的 url
        webLog.setUrl(url);
        // http://ip:port/
        webLog.setBasePath(StrUtil.removeSuffix(url, URLUtil.url(url).getPath()));
        // 获取用户的 id 并放入
        webLog.setUsername(authentication == null ? "anonymous" : authentication.getPrincipal().toString());
        webLog.setIp(IpUtils.getIpAddr(request)); // TODO 获取ip 地址 使用 IPUtils
        // 获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取类的名称
        String targetClassName = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取方法
        Method method = signature.getMethod();
        // 因为我们会使用 Swagger 这工具，我们必须在方法上面添加@ApiOperation(value="")该注解, 这个注解的值就是方法的描述
        // 获取ApiOperation
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
        // 设置方法的描述
        webLog.setDescription(annotation == null ? "no desc" : annotation.value());
        // com.bjsxt.controller.UserController.login()
        webLog.setMethod(targetClassName + '.' + method.getName());
        // 设置方法的参数 {"key_参数的名称": "value_参数的值"}
        webLog.setParameter(getMethodParameter(method, proceedingJoinPoint.getArgs()));
        // 设置方法的返回结果
        webLog.setResult(result);
        // 输出日志
        log.info(JSON.toJSONString(webLog, true));
        // 返回对象
        return result;
    }
    /**
     * 获取方法的执行参数
     *
     * @param method 方法对象
     * @param args   参数对象
     * @return 参数 json 字符串
     */
    private Object getMethodParameter(Method method, Object[] args) {
        // 返回的 map 集合对象
        Map<String, Object> methodParametersWithValues = new HashMap<>();
        // 获取方法形参列表类
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        // 方法的形参名称
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        // 进行方法形参列表的遍历
        for (int i = 0; i < parameterNames.length; i++) {
            // 如果方法的参数名称是密码或者是文件那么就是受限的类型, 不进行放入日志激励
            if (parameterNames[i].equals("password") || parameterNames[i].equals("file")) {
                // 放入受限的类型
                methodParametersWithValues.put(parameterNames[i], "受限的支持类型");
            } else {
                // 正常放入
                methodParametersWithValues.put(parameterNames[i], args[i]);
            }
        }
        // 进行 map 对象的返回
        return methodParametersWithValues;
    }
}
