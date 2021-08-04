package com.weiyx.nos.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
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
import java.util.List;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    /**
     * swagger的api json文档路径
     */
    public static final String API_URI = "/v2/api-docs";
    /**
     * Eureka发现功能的方法的名字，注册的服务会加入这个前缀
     */
    public static final String EUREKA_SUB_PFIX = "CompositeDiscoveryClient_";
    /**
     * 服务发现的路由处理器
     */
    private final DiscoveryClientRouteDefinitionLocator routeLocator;

    public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();

        List<String> routes = new ArrayList<>();
        //从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            resources.add(swaggerResource(
                    //获取id(服务注册的id)
                    routeDefinition.getId()
                            //去除CompositeDiscoveryClient_前缀
                            .substring(EUREKA_SUB_PFIX.length()),
                    //获取路由定义信息列表
                    routeDefinition.getPredicates()
                            //获取路径信息PredicateDefinition{name='Path', args={pattern=/byb-provider2/**}}
                            .get(0)
                            .getArgs()
                            //将pattern中的/**替换为服务swagger文档路径
                            .get("pattern")
                            .replace("/**", API_URI)));
        });
        //for是我自己写的过滤没有api文档的代码，自己视情况添加
        for (SwaggerResource swaggerResource : resources) {
            //获取所有服务的文档uri
            String uri = swaggerResource.getUrl();
            //获取服务名
            String serverName = uri.replace(API_URI, "");
            //拼接url
            String url = "http://regist.byb.com:38001/eureka/apps"+serverName;
            //获取RestTemplate实例
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            //添加请求头
            httpHeaders.add("Content-Type", "application/json");
            httpHeaders.add("Accept", "application/json");
            //请求体
            HttpEntity<String> httpEntity = new HttpEntity<String>(null,httpHeaders);
            //发送请求获取响应
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            String response = responseEntity.getBody();
            //解析json
            JSONObject jsonObject;
            jsonObject = JSONObject.parseObject(response);
            String str1 = jsonObject.getString("application");

            jsonObject = JSONObject.parseObject(str1);
            String str2 = jsonObject.getString("instance");

            JSONArray jsonArray = JSONObject.parseArray(str2);
            String str3 = jsonArray.getString(0);

            jsonObject = JSONObject.parseObject(str3);
            String serverUrl = jsonObject.getString("homePageUrl");
            if (null != serverUrl && !"".equals(serverUrl)) {
                //拼接服务api文档的url
                String serverApi = serverUrl + "v2/api-docs";
                //发送请求
                try {
                    URL url1 = new URL(serverApi);
                    HttpURLConnection  httpUrlConn = (HttpURLConnection) url1.openConnection();
                    //设置连接超时时间
                    httpUrlConn.setConnectTimeout(1000);
                    //获取返回码
                    int code = httpUrlConn.getResponseCode();
                    if (code != 200) {
                        resources.remove(swaggerResource);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {

        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
