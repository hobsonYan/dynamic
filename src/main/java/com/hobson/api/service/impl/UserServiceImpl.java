package com.hobson.api.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hobson.api.model.User;
import com.hobson.api.mapper.UserMapper;
import com.hobson.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-03
 */
@Service
@DS("master")
public class UserServiceImpl  implements IUserService {

    @Resource
    private UserMapper userMapper;

    public User getUser() {

        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, "123@hobson.com"));
    }
}