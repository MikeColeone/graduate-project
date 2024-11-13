package org.example.graduatemanage.components;

import lombok.RequiredArgsConstructor;
import org.example.graduatemanage.interceptor.AdminInterceptor;
import org.example.graduatemanage.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/api/Admin/**").excludePathPatterns("/api/login");
    }
}
