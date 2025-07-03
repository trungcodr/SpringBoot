package com.example.login_logout1.config;

import com.example.login_logout1.security.JwtFilter;
import com.example.login_logout1.security.JwtUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public JwtFilter jwtFilterBean(JwtUtil jwtUtil) {
        return new JwtFilter(jwtUtil);
    }
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtFilter filter) {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.addUrlPatterns("/*"); // Áp dụng cho mọi endpoint
        bean.setOrder(1); // Ưu tiên chạy sớm nếu có nhiều filter
        return bean;
    }
}
