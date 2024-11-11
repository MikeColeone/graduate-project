//package org.example.graduatemanage.components;
//
//import lombok.RequiredArgsConstructor;
//import org.example.springmvcexamples.interceptor.AdminInterceptor;
//import org.example.springmvcexamples.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//    private final LoginInterceptor loginInterceptor;
//    private final AdminInterceptor adminInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){//一个星一层
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login");
//        registry.addInterceptor(adminInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login");
//    }
//}
