package com.hobson.api.service.impl;

import com.hobson.api.model.Role;
import com.hobson.api.model.User;
import com.hobson.api.model.UserRole;
import com.hobson.api.service.IRoleService;
import com.hobson.api.service.IUserRoleService;
import com.hobson.api.service.IUserService;
import com.hobson.api.service.TestDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
//
    @Resource
    private IUserService iUserService;

    @Resource
    private IRoleService iRoleService;

    @Resource
    private IUserRoleService iUserRoleService;

    @Override
    public Map<String, Object> test() {
//
        Map<String, Object> result = new HashMap<>();
        User user = iUserService.getUser();
        result.put("user", user);
        Role role = iRoleService.getRole();
        result.put("role", role);

        UserRole userRole = new UserRole();
        userRole.setId(0d);
        userRole.setUserId(Double.valueOf(user.getId()));
        userRole.setRoleId(Double.valueOf(role.getId()));
        iUserRoleService.insert(userRole);
        UserRole userRole1 = iUserRoleService.getUserRole();
        result.put("userRole", userRole1);
        return result;
    }
}
