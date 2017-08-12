package com.example.springbootAll.controller;

import com.example.springbootAll.repository.priRepository.entity.PriUser;
import com.example.springbootAll.repository.secRepository.entity.SecUser;
import com.example.springbootAll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sea
 * @Description: 用户测试控制器
 * @Date: 9:42 2017/8/12
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/secUsers")
    public List<SecUser> findUsers() {
        return userService.findSecUsers();
    }

    @GetMapping("/priUsers")
    public List<PriUser> findPriUsers(){return userService.findPriUsers();}
}
