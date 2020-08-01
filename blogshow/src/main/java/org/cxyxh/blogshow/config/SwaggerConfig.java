package org.cxyxh.blogshow.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/11 21:09
 * @describetion :
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.cxyxh"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("程序员小黄博客系统_前台模块")
//                        .description("程序员小黄博客系统_后台模块_个人学习")
                        .version("1.0")
                        .contact(new Contact("程序员小黄博客系统", "http://www.cxyxh.xyz", "54006022@qq.com"))
                        .license("the Apache lice")
                        .licenseUrl("http://www.cxyxh.xyz")
                        .build()
                );
    }
}
