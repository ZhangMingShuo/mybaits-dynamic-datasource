package com.simple.mybaitsdynamicdatasource.web.controller;

import com.simple.mybaitsdynamicdatasource.infrastructure.db.entity.UserInfoEntity;
import com.simple.mybaitsdynamicdatasource.infrastructure.service.TenantService;
import com.simple.mybaitsdynamicdatasource.infrastructure.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@RestController
@RequestMapping("/user-info")
@AllArgsConstructor
public class UserInfoController {
    private UserInfoService userInfoService;

    private TenantService tenantService;

    @GetMapping("/query/{tenantId}")
    public List<UserInfoEntity> query(@PathVariable String tenantId) {
        tenantService.changeDsByTenantId(tenantId);
        return userInfoService.list();
    }

    @GetMapping("/query")
    public List<UserInfoEntity> queryAll() {
        return userInfoService.list();
    }
}
