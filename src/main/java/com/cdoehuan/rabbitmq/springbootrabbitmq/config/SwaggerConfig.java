package com.cdoehuan.rabbitmq.springbootrabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: ZhangHuan created on 2022/2/27 22:04
 * @Email: codehuan@163.com
 * @Version: 1.0
 * @Description: swagger
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("rabbitmq接口文档")
                .description("本文档描述了rabbitmq 微服务接口定义")
                .version("1.0")
                .contact(new Contact("codehuan", "https://www.codehuan.cn", "1730272469@qq.com"))
                .build();
    }
}
