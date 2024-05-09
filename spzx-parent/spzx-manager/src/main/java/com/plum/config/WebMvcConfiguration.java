package com.plum.config;

import com.plum.filter.LoginAuthInterceptor;
import com.plum.properties.UserAuthProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfiguration
 * Package: com.plum.config
 * description
 * Author: Plum
 * Crete : 2024/5/8 21:02
 * Version 1.0
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private UserAuthProperties userAuthProperties;
    @Resource
    private LoginAuthInterceptor loginAuthInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");                // 允许所有的请求头
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor).excludePathPatterns(
                userAuthProperties.getNoAuthUrls()
        ).addPathPatterns("/**");
    }
}
