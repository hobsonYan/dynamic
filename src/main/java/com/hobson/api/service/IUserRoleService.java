package com.hobson.api.service;

import com.hobson.api.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-08
 */
public interface IUserRoleService {

    void insert(UserRole userRole);

    UserRole getUserRole();
}
