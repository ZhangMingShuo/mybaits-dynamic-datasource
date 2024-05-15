package com.simple.mybaitsdynamicdatasource.infrastructure.db.model;

import lombok.Data;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Data
public class RedisInfo {

    /**
     * 租户redis host
     */
    private String host;
    /**
     * 租户redis port
     */
    private Integer port;
    /**
     * 租户redis password
     */
    private String pwd;
    /**
     * 租户redis database
     */
    private Integer db;
}
