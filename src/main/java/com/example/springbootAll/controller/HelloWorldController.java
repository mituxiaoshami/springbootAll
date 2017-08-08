package com.example.springbootAll.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sea
 * @Description: hello 测试类
 * @Date: 10:04 2017/8/5
 */
//RestController 的意思就是controller里面的方法都以json格式输出，不用写什么jackson配置
@RestController
public class HelloWorldController {

    /**
     * @Author: sea
     * @Description:
     * @Date: 10:10 2017/8/5
     */
    @RequestMapping("/hello")
    public String index() {
        return "Hello World!";
    }

}
