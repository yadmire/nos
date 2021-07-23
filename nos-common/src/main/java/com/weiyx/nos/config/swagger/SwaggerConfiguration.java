package com.weiyx.nos.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfiguration {
    @Autowired
    private SwaggerProperties swaggerProperties;
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiinfo())
                .select()
                // 扫描那个包生成 swagger 注解
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        // 安全的配置
//        docket.securitySchemes(securitySchemes()) // 安全规则
//                .securityContexts(securityContexts()); // 安全配置的上下文
        return docket;
    }

    private ApiInfo apiinfo() {
        return new ApiInfoBuilder().contact(new Contact(swaggerProperties.getName(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                // 设置标题
                .title(swaggerProperties.getTitle())
                // 设置描述信息
                .description(swaggerProperties.getDescription())
                // 设置版本号
                .version(swaggerProperties.getVersion())
                .build();
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath*:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath*:/META-INF/resources/webjars/");
//    }
}
