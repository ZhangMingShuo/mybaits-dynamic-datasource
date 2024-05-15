package com.simple.mybaitsdynamicdatasource.infrastructure.config;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
public class TenantContext {

    public static String TENANT_ID_HEADER = "X-TENANT-ID";
    public static String DEFAULT_TENANT_ID = "0";

    private static final ThreadLocal<String> TENANT_LOCAL = ThreadLocal.withInitial(() -> DEFAULT_TENANT_ID);

    public TenantContext() {
    }

    public static String getTenant() {
        return TENANT_LOCAL.get();
    }

    public static void setTenant(String tenant) {
        TENANT_LOCAL.set(tenant);
    }

    public static void remove() {
        TENANT_LOCAL.remove();
    }
}
