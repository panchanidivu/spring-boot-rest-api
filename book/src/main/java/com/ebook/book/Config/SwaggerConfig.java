package com.ebook.book.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ebook.book.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
        // .securitySchemes(basicScheme());
        
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "EBook management API", 
          "Some custom description of API.", 
          "API TOS", 
          "Terms of service", 
          new Contact("divyesh patel", "www.divyesh.com", "xyz@asd.com"), 
          "License of API", "API license URL", Collections.emptyList());
    }
    
    
    
    //   private List<SecurityScheme> basicScheme() {
    //     List<SecurityScheme> schemeList = new ArrayList<>();
    //     schemeList.add(new BasicAuth("basicAuth"));
    //     return schemeList;
    // }
    
    

    

    

    
}