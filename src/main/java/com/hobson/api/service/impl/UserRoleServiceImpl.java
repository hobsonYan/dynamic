package com.hobson.api.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hobson.api.mapper.UserRoleMapper;
import com.hobson.api.model.UserRole;
import com.hobson.api.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-08
 */
@Service
@DS("oracle")
public class UserRoleServiceImpl implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
    }

    @Override
    public UserRole getUserRole() {
        return userRoleMapper.selectOne(
                Wrappers.<UserRole>lambdaQuery()
                        .eq(UserRole::getUserId, 1));
    }
}
