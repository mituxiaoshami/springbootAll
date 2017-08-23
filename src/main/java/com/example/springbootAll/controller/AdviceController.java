package com.example.springbootAll.controller;

import com.example.springbootAll.common.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sea
 * @Description: 异常测试控制器
 * @Date: 11:17 2017/8/23
 */
@RestController
@RequestMapping("exception")
public class AdviceController {

    @GetMapping("/ex1")
    public String hello1() {
        int i = 1 / 0;
        return "hello";
    }

    @GetMapping("/ex2")
    public String hello2(Long id) {
        String string = null;
        string.length();
        return "hello";
    }

    @GetMapping("/ex3")
    public List<String> hello3() {
        throw new ServiceException("test");
    }

}
