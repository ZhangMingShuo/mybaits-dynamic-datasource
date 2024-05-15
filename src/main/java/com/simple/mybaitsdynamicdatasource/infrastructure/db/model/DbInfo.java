package com.simple.mybaitsdynamicdatasource.infrastructure.db.model;

import lombok.Data;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Data
public class DbInfo {
    /**
     * 租户数据库URL
     */
    private String dbUrl;

    /**
     * 租户数据库用户名
     */
    private String dbUsername;

    /**
     * 租户数据库密码
     */
    private String dbPassword;

    /**
     * 租户数据库驱动类
     */
    private String dbDriverClassName;
}
