package com.simple.mybaitsdynamicdatasource.infrastructure.config.handler;

import com.simple.mybaitsdynamicdatasource.infrastructure.config.TenantContext;
import com.simple.mybaitsdynamicdatasource.infrastructure.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class TenantDsInterceptor implements HandlerInterceptor {

    private TenantService tenantDsService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //todo 从请求中获取租户ID
//        String tenantId = "1";
//        TenantContext.setTenant(tenantId);
        //根据tenantId切换数据源
//        tenantDsService.changeDsByTenantId(tenantId);
        return true;
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //清空当前线程数据源
        tenantDsService.clearDsContext();
    }

}
