package com.example.springbootAll.sender;

import com.example.springbootAll.entity.priEntity.PriUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: sea
 * @Description: RabbitMQ消息发送者
 * @Date: 20:45 2017/8/14
 */
@Component
public class RabbitMqSender {

    Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        Date date = new Date();
        String context = "hello " +date;
     //   logger.info("Sender:"+context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }

    public void send(int i) {
        String context = "hello";
        this.rabbitTemplate.convertAndSend("hello",context+"--------"+i);
    }

    //对象的支持 springboot以及完美的支持对象的发送和接收，不需要格外的配置
    public void send(PriUser priUser) {
        logger.info("Sender PriUser:"+priUser.toString());
        this.rabbitTemplate.convertAndSend("priUser",priUser);
    }

    //测试Topic 规则下的消息队列
    //发送send1会匹配到topic.#和topic.message 两个Receiver都可以收到消息 topic.message既和topic.message匹配又和topic.#相匹配
    public void sendTopicMessage() {
        String message = "hi, I am message 1";
        logger.info("Sender:"+message);
        this.rabbitTemplate.convertAndSend("exchange","topic.message",message);
    }

    //发送send2只有topic.#可以匹配所有只有Receiver2监听到消息。 因为和topic.message队列不匹配
    public void sendTopicMessages() {
        String message = "hi, I am message 2";
        logger.info("Sender:"+message);
        this.rabbitTemplate.convertAndSend("exchange","topic.messages",message);
    }

    public void sendFanoutMessage() {
        String message = "hi, fanout msg";
        logger.info("Sender: "+message);
        this.rabbitTemplate.convertAndSend("fanoutExchange","",message);
    }

}
