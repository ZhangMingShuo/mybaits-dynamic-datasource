package com.simple.mybaitsdynamicdatasource.infrastructure.service.impl;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.mybaitsdynamicdatasource.infrastructure.config.TenantContext;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.entity.TenantEntity;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.mapper.TenantMapper;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.model.DbInfo;
import com.simple.mybaitsdynamicdatasource.infrastructure.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;


/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantEntity> implements TenantService {

    private TenantMapper tenantMapper;

    private DynamicRoutingDataSource dataSource;

    private DefaultDataSourceCreator dataSourceCreator;


    /**
     * 根据租户ID切换数据源
     *
     * @param tenantId 租户ID
     */
    @Override
    public void changeDsByTenantId(String tenantId) {
        //当前租户ID对应的数据源已存在，则直接切换
        if (existInMemory(tenantId)) {
            //切换数据源
            changeTenantDs(tenantId);
            return;
        }
        DataSource dataSource = queryTenantIdToDataSource(tenantId);
        if (!ObjectUtils.isEmpty(dataSource)) {
            //动态添加数据源
            this.dataSource.addDataSource(tenantId, dataSource);
            //切换数据源
            this.changeTenantDs(tenantId);
            return;
        }
        // todo 抛出异常信息
        throw new RuntimeException("数据源不存在");
    }

    /**
     * 判断是否存在内存中
     * @param dsName
     * @return
     */
    @Override
    public Boolean existInMemory(String dsName) {
        return StringUtils.hasText(dsName) && dataSource.getDataSources().containsKey(dsName);
    }

    /**
     * 清理当前调用上下文中的数据源缓存
     */
    @Override
    public void clearDsContext() {
        //清空当前线程数据源
        DynamicDataSourceContextHolder.clear();
        TenantContext.remove();
    }

    /**
     * 移除对应的数据源信息
     *
     * @param dsName 数据源名称
     */
    @Override
    public void removeDs(String dsName) {
        dataSource.removeDataSource(dsName);
    }


    /**
     * 切换租户对应的数据源
     *
     * @param tenantId 租户ID即对应数据源名称
     */
    private void changeTenantDs(String tenantId) {
        log.debug("切换数据源：{}", tenantId);
        //设置租户上下文
        TenantContext.setTenant(tenantId);
        //根据tenantId切换数据源
        DynamicDataSourceContextHolder.push(tenantId);
    }

    /**
     * 根据租户ID查询数据源连接信息，并生成数据源
     *
     * @param tenantId
     * @return
     */
    private DataSource queryTenantIdToDataSource(String tenantId) {
        TenantEntity tenant = tenantMapper.selectById(tenantId);
        log.debug("find db tenant info by tenantId:{}", tenantId);
        //租户为空则直接返回空
        if (!StringUtils.hasText(tenantId) || ObjectUtils.isEmpty(tenant)) {
            // todo 返回业务异常信息
            return null;
        }
        DbInfo dbInfo = JSON.parseObject(tenant.getDbInfo(), DbInfo.class);
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl(dbInfo.getDbUrl());
        dataSourceProperty.setUsername(dbInfo.getDbUsername());
        dataSourceProperty.setPassword(dbInfo.getDbPassword());
        dataSourceProperty.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSourceProperty.setDruid(new DruidConfig());
        return this.dataSourceCreator.createDataSource(dataSourceProperty);
    }
}
