package com.example.springbootAll.service;



import com.example.springbootAll.entity.priEntity.PriUser;
import com.example.springbootAll.entity.secEntity.SecUser;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: sea
 * @Description: 用户业务逻辑接口层
 * @Date: 9:42 2017/8/12
 */
public interface UserService {

     List<SecUser> findSecUsers();

     List<PriUser> findPriUsers();

     Page<PriUser> findPagePriUsers();
}
