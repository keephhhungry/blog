package org.cxyxh.blogshow.config;

import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/11 11:01
 * @describetion :
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**",//前面是swagger
                "/","/login","/verifyCode","/js/**","/css/**","/images/**","/tinymce/**","/fonts/**","/index.html","/favicon.ico");
    }
}
