package com.hobson.api.controller;


import com.hobson.api.model.User;
import com.hobson.api.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yan hongbo
 * @since 2020-04-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @GetMapping("/get")
    @ResponseBody
    public User get() {

        return iUserService.getUser();
    }
}

