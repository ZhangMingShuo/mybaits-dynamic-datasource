package com.simple.mybaitsdynamicdatasource.infrastructure.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple.mybaitsdynamicdatasource.infrastructure.db.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:benxiong.hu
 * @CreateAt:2024/5/15
 * @ModifyAt:2024/5/15
 * @Version:1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
}
