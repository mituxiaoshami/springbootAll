package com.example.springbootAll.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sea
 * @Description: RabbitMQ 消息队列配置
 * @Date: 20:44 2017/8/14
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

    @Bean Queue priUser() {
        return new Queue("priUser");
    }
}
