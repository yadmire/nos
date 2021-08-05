package com.weiyx.nos.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    /**
     * swagger的api json文档路径
     */
    public static final String API_URI = "/v2/api-docs";
    @Value("${spring.application.name}")
    private String self;
    /**
     * Eureka发现功能的方法的名字，注册的服务会加入这个前缀
     */
    public static final String EUREKA_SUB_PFIX = "CompositeDiscoveryClient_";
    /**
     * 服务发现的路由处理器
     */
    private final RouteLocator routeLocator;

    public SwaggerProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();

        List<String> routeHosts = new ArrayList<>();
        //从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
        routeLocator.getRoutes().filter(route -> route.getUri().getHost()!=null)
                .filter(route -> !self.equals(route.getUri().getHost()))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
        Set<String> dealed = new HashSet<>();
        routeHosts.forEach(instance ->{
            String url = "/" + instance + API_URI;
            if(!dealed.contains(url)){
                dealed.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(instance);
                swaggerResource.setSwaggerVersion("2.0");
                resources.add(swaggerResource);
            }
        });

        return resources;
    }
}
