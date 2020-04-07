package com.hobson.api.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hobson.api.model.Role;
import com.hobson.api.mapper.RoleMapper;
import com.hobson.api.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-07
 */
@Service
@DS("slave")
public class RoleServiceImpl  implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    public Role getRole() {
        return roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleName, "admin"));
    }

}
