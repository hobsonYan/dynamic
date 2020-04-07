package com.hobson.api.controller;

import com.hobson.api.service.TestDynamicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yan hongbo
 * @date 2020/4/7 19:49
 * @description
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @Resource
    private TestDynamicService testDynamicService;

    @GetMapping("/get")
    @ResponseBody
    public Map<String, Object> get() {
        return testDynamicService.test();
    }
}
