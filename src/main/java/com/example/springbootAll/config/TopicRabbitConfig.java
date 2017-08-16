package com.example.springbootAll.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sea
 * @Description: topic规则配置 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列。
 * @Date: 18:38 2017/8/15
 */
@Configuration
public class TopicRabbitConfig {

    public final static String message = "topic.message";
    public final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    //使用queueMessages同时匹配两个队列，queueMessage只匹配”topic.message”队列
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
