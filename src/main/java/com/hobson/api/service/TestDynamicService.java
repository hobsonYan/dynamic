package com.hobson.api.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author yan hongbo
 * @date 2020/4/3 14:46
 * @description
 */
public interface TestDynamicService {

    Map<String, Object> test();
}
