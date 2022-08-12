package com.liyk.apps.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyongkui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/8 17:35
 */
@RestController
@Api(tags = "HelloController测试")
public class HelloController {

    @ApiOperation(value = "登录", notes = "校验登录是否成功")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    //method 不写的话，默认GET、POST都支持，根据前端方式自动适应。
    public String hello() {
        return "hello springbooot";
    }
}
