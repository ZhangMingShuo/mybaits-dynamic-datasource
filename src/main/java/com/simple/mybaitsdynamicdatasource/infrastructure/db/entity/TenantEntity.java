package com.simple.mybaitsdynamicdatasource.infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

import java.util.Date;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Data
@TableName("tenant")
public class TenantEntity {

    private Long id;

    private String tenantName;

    private String tenantDesc;

    private String dbInfo;

    private String redisInfo;

    @Version
    private Integer version;

    private Date createdTime;

    private String createdBy;

    private Date modifiedTime;

    private String modifiedBy;

    private Integer isDeleted;

}
