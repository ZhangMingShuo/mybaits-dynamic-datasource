# mybaits-dynamic-datasource
mybaits-dynamic-datasource simple demo
## 
https://juejin.cn/post/7369124076068339739
```java
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
```