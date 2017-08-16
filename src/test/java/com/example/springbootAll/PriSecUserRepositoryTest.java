package com.example.springbootAll;

import com.example.springbootAll.entity.priEntity.PriUser;
import com.example.springbootAll.repository.priRepository.PriUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:41 2017/8/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriSecUserRepositoryTest {

    Logger logger = LoggerFactory.getLogger(PriSecUserRepositoryTest.class);

    @Autowired
    private PriUserRepository priUserRepository;

    @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        //因为数据库配置了唯一性 所以不能重复添加
   //     userRepository.save(new User("aa1","aa123456", "aa@126.com", "aa", formattedDate));
    //    userRepository.save(new User("bb2","bb123456", "bb@126.com", "bb", formattedDate));
   //     userRepository.save(new User("cc3","cc123456", "cc@126.com", "cc", formattedDate));

        Assert.assertEquals(3, priUserRepository.findAll().size());
        Assert.assertEquals("cc", priUserRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        PriUser user = this.priUserRepository.findByUserName("aa1");
        priUserRepository.delete(user);
    }

    //测试分页查询
    @Test
    public void testPageQuery() throws Exception {
        int page=1,size=10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<PriUser> priUsers = priUserRepository.findAll(pageable);
        logger.info("------------------------priUsers"+priUsers);
        priUserRepository.findByUserName("testName", pageable);

        List<PriUser> userList= this.priUserRepository.findAll();
        for (PriUser user:userList) {
            System.out.println(user.getUserName());
        }
    }

}
