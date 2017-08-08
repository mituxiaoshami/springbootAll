package com.example.springbootAll;

import com.example.springbootAll.entity.User;
import com.example.springbootAll.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:41 2017/8/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1","aa123456", "aa@126.com", "aa", formattedDate));
        userRepository.save(new User("bb2","bb123456", "bb@126.com", "bb", formattedDate));
        userRepository.save(new User("cc3","cc123456", "cc@126.com", "cc", formattedDate));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("cc", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));
    }

}
