package com.simple.mybaitsdynamicdatasource.infrastructure.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Data
@TableName("user_info")
public class UserInfoEntity {

    private Long id;

    private String userNo;

    private String userName;

    private String description;

    private Date createdTime;

    private String createdBy;

    private Date modifiedTime;

    private String modifiedBy;

    private Integer isDeleted;
}
