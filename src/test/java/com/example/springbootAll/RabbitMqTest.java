package com.example.springbootAll;

import com.example.springbootAll.entity.priEntity.PriUser;
import com.example.springbootAll.repository.priRepository.PriUserRepository;
import com.example.springbootAll.sender.RabbitMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: sea
 * @Description: RabbitMq 测试类
 * @Date: 21:10 2017/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private PriUserRepository priUserRepository;

    @Autowired
    private RabbitMqSender rabbitMqSender;

    @Autowired
    private RabbitMqSender mqSender;


    @Test
    public void sender() throws Exception{
            rabbitMqSender.send();
    }

    //一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中。
    @Test
    public void oneToManySender() throws Exception{
        for (int i=0 ;i<50;i++) {
            rabbitMqSender.send(i);
        }
    }

    // 结论和一对多一样，接收端仍然会均匀接收到消息。
    @Test
    public void ManyToManySender() throws Exception{
        for (int i=0 ;i<50;i++) {
            mqSender.send(i);
            rabbitMqSender.send(i);
        }
    }

    //测试对象消息传送
    @Test
    public void PriUserSender() throws Exception{
        List<PriUser> priUsers = this.priUserRepository.findAll();
        for (PriUser priUser : priUsers) {
            this.rabbitMqSender.send(priUser);
        }
    }

    //测试Topic规则下 发送topic.message
    @Test
    public void TopicMessage() throws Exception{
        this.rabbitMqSender.sendTopicMessage();
    }

    //测试Topic规则下 发送topic.messages
    @Test
    public void TopicMessages() throws Exception{
        this.rabbitMqSender.sendTopicMessages();
    }

    @Test
    public void FanoutMessage() throws Exception{
        this.rabbitMqSender.sendFanoutMessage();
    }

}
