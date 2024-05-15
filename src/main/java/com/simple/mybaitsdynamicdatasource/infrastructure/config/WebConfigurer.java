package com.simple.mybaitsdynamicdatasource.infrastructure.config;

import com.simple.mybaitsdynamicdatasource.infrastructure.config.handler.TenantDsInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Configuration
@AllArgsConstructor
public class WebConfigurer implements WebMvcConfigurer {

    private TenantDsInterceptor tenantDsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantDsInterceptor).addPathPatterns("/**");
    }

}
