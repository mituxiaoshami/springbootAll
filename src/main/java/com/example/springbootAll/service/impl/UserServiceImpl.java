package com.example.springbootAll.service.impl;

import com.example.springbootAll.entity.SortForm;
import com.example.springbootAll.entity.priEntity.PriUser;
import com.example.springbootAll.repository.priRepository.PriUserRepository;
import com.example.springbootAll.entity.secEntity.SecUser;
import com.example.springbootAll.repository.secRepository.SecUserRepository;
import com.example.springbootAll.service.UserService;
import com.example.springbootAll.util.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @Author: sea
 * @Description: 用户业务逻辑实现层
 * @Date: 9:43 2017/8/12
 */
@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    public Page<PriUser> findPagePriUsers() {
        Pageable pageable = new PageRequest(0,5);

        //原生的分页
        //Page<PriUser> priUsers = priUserRepository.findAll(pageable);
        //logger.info("总页数:"+priUsers.getTotalPages());
        //logger.info("总条数:"+priUsers.getTotalElements());
        //return priUsers;

        // 测试分页工具 (使用默认的每页条目数 和排序字段、排序方式)
        //Page<PriUser> priUsers1 = priUserRepository.findAll(PageUtils.basicPage(0));
        //return priUsers1;

        // 测试分页工具 (传页码和条数)
        //Page<PriUser> priUsers2 = priUserRepository.findAll(PageUtils.basicPage(0,5));
        //return priUsers2;

        // 测试分页工具 (传页码、条数、排序)
        //Page<PriUser> priUsers3 = priUserRepository.findAll(PageUtils.basicPage(0,5,new SortForm("id")));
        //return priUsers3;

        Page<PriUser> priUsers4 = priUserRepository.findAll(PageUtils.basicPage(0,5,new SortForm("ASC","id")));
        return priUsers4;
    }

}
