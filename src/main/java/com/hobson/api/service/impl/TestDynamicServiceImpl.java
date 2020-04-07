package com.hobson.api.service.impl;

import com.hobson.api.mapper.UserMapper;
import com.hobson.api.model.Role;
import com.hobson.api.model.User;
import com.hobson.api.service.IRoleService;
import com.hobson.api.service.IUserService;
import com.hobson.api.service.TestDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yan hongbo
 * @date 2020/4/3 14:46
 * @description
 */
@Service
@Slf4j
public class TestDynamicServiceImpl implements TestDynamicService {

    @Resource
    private IUserService iUserService;

    @Resource
    private IRoleService iRoleService;

    @Override
    public Map<String, Object> test() {

        Map<String, Object> result = new HashMap<>();
        User user = iUserService.getUser();
        result.put("user", user);
        Role role = iRoleService.getRole();
        result.put("role", role);
        return result;
    }
}
