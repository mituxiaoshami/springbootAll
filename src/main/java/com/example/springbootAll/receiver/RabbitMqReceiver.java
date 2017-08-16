package com.example.springbootAll.receiver;

import com.example.springbootAll.config.TopicRabbitConfig;
import com.example.springbootAll.entity.priEntity.PriUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: sea
 * @Description:
 * @Date: 10:24 2017/8/16
 */
@Component
public class RabbitMqReceiver {

    Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitHandler
    //发送者和接收者的queue name必须一致，不然不能接收
    @RabbitListener(queues = "hello")
    public void processPriHello(String hello) {
        logger.info("ReceiverPri: "+hello);
    }

    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void processSecHello(String hello) {
        logger.info("ReceiverSec: "+hello);
    }

    @RabbitHandler
    @RabbitListener(queues = "priUser")
    public void processPriUser(PriUser priUser) {
        logger.info("ReceiverPriUser:"+priUser);
    }

    @RabbitHandler
    @RabbitListener(queues = TopicRabbitConfig.message)
    public void processMessage(String messgae) {
        logger.info("ReceiverTocpicMessage:"+messgae);
    }

    @RabbitHandler
    @RabbitListener(queues = TopicRabbitConfig.messages)
    public void procesMessages(String messgae) {
        logger.info("ReceiverTocpicMessages:"+messgae);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void procesFanoutAMessage(String messgae) {
        logger.info("ReceiverFanoutAMessage:"+messgae);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void procesFanoutBMessage(String messgae) {
        logger.info("ReceiverFanoutBMessage:"+messgae);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void procesFanoutCMessage(String messgae) {
        logger.info("ReceiverFanoutCMessage:"+messgae);
    }
}
