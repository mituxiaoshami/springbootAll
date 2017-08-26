package com.example.springbootAll.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @Author: sea
 * @Description: 在项目启动的时候，就去加载一些数据或做一些事情 为了解决这样的问题，
 *                Spring Boot 为我们提供了一个方法，通过实现接口 CommandLineRunner 来实现。
 * @Date: 16:33 2017/8/24
 */
@Component
// 利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序 @Order 注解的执行优先级是按value值从小到大顺序。
@Order(value = 1)
public class MyStartupRunner implements CommandLineRunner{

    private static Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        for (String arg:args) {
            logger.info(arg);
        }
    }
}
