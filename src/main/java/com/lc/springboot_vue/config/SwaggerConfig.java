package com.lc.springboot_vue.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Lc
 * @Date 2023/4/2
 * @Description
 */
//@Configuration
//@OpenAPIDefinition
//@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lc"))//基本包名
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchemes()))
                .securityContexts(Collections.singletonList(securityContexts()));

    }

    private SecurityScheme securitySchemes() {
        return new ApiKey("X-Token", "X-Token", "header");
    }

    private SecurityContext securityContexts() {
      return SecurityContext.builder()
              .securityReferences(defaultAuth())
              .forPaths(PathSelectors.regex("^(?!auth).*$"))
              .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("X-Token", authorizationScopes));
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("坤坤接口文档")
                .description("springboot+vue的ikun平台")
                .version("1.0")
                .contact(new Contact("lc","http://www.bilibili.com","1641767@qq.com"))
                .build();
    }
}


