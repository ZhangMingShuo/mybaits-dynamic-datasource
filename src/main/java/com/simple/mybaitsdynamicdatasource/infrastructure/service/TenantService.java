package com.simple.mybaitsdynamicdatasource.infrastructure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.entity.TenantEntity;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
public interface TenantService extends IService<TenantEntity> {

        /**
     * 根据租户ID切换数据源
     *
     * @param tenantId 租户ID
     */
    void changeDsByTenantId(String tenantId) ;

    /**
     * 当前应用是否已在内存中加载过此数据源
     *
     * @param dsName
     * @return
     */
    Boolean existInMemory(String dsName);

    /**
     * 清理当前调用上下文中的数据源缓存
     */
    void clearDsContext();

    /**
     * 移除对应的数据源信息
     *
     * @param dsName 数据源名称
     */
    void removeDs(String dsName);
}
