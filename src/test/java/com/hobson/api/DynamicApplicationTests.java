package com.hobson.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hobson.api.mapper.UserMapper;
import com.hobson.api.model.User;
import com.hobson.api.service.TestDynamicService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DynamicApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testMybatis_Plus() {

//        User user = userMapper.selectOne(
//                Wrappers.<User>lambdaQuery()
//                        .ne(User::getEmail, "123@hobson.com")
//        );
//        User user = userMapper.selectByEmail("123@hobson.com");
//        System.out.println(user);
    }
}
