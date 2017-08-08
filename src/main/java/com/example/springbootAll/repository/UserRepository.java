package com.example.springbootAll.repository;

import com.example.springbootAll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:29 2017/8/8
 */
//UserRepository 继承JpaRepository 具备了一些jpa自带的增删改查方法
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

}
