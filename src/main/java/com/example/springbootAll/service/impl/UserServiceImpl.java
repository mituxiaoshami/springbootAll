package com.example.springbootAll.service.impl;

import com.example.springbootAll.repository.priRepository.entity.PriUser;
import com.example.springbootAll.repository.priRepository.repository.PriUserRepository;
import com.example.springbootAll.repository.secRepository.entity.SecUser;
import com.example.springbootAll.repository.secRepository.repository.SecUserRepository;
import com.example.springbootAll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: sea
 * @Description: 用户业务逻辑实现层
 * @Date: 9:43 2017/8/12
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private SecUserRepository secUserRepository;

    @Autowired
    private PriUserRepository priUserRepository;

    public List<SecUser> findSecUsers() {
        List<SecUser> secUsers = secUserRepository.findAll();
        return secUsers;
    }

    public List<PriUser> findPriUsers() {
        List<PriUser> priUsers = priUserRepository.findAll();
        return priUsers;
    }

}
