package com.simple.mybaitsdynamicdatasource.infrastructure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.entity.UserInfoEntity;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.mapper.UserInfoMapper;
import com.simple.mybaitsdynamicdatasource.infrastructure.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
}
