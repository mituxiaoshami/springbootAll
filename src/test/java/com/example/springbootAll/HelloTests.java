package com.example.springbootAll;

import com.example.springbootAll.controller.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @Author: sea
 * @Description:
 * @Date: 10:12 2017/8/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
//测试用例的注解，1.4以后SpringApplicationConfiguration注解被SpringBootTest注解取代了
@SpringBootTest
//使用MockMvc对我们的系统的Controller进行单元测试
@WebAppConfiguration
//如果有对数据库进行操作，配置事务的回滚 此时需要自己配置transactionManager这个Bean
//@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
//@Transactional
public class HelloTests {

    private MockMvc mvc;

    //这个方法在每个方法执行之前都会执行一遍
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build(); //初始化MockMvc对象
    }

    @Test
    public void getHello() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
