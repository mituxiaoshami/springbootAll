package com.example.springbootAll;

import com.example.springbootAll.controller.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

        /**
         * perform:执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
         *
         * get:声明发送一个get请求的方法。MockHttpServletRequestBuilder get(String urlTemplate, Object… urlVariables)：
         * 根据uri模板和uri变量值得到一个GET请求方式的。另外提供了其他的请求的方法，如：post、put、delete等。
         *
         * param：添加request的参数，如上面发送请求的时候带上了了pcode = root的参数。
         * 假如使用需要发送json数据格式的时将不能使用这种方式，可见后面被@ResponseBody注解参数的解决方法
         *
         * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）
         *
         * andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）
         *
         * andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）
         */
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
