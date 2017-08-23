package com.example.springbootAll;

import com.example.springbootAll.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * @Author: sea
 * @Description:
 * @Date: 14:59 2017/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME = "emailTemplate";

    /**
     * 发送简单文本
     * @throws Exception`
     */
    @Test
    public void testSimpleMail() throws Exception{
        this.mailService.sendSimpleMail("18968249632@163.com","test simple mail"," hello this is simple mail");
    }

    /**
     * 发送HTML格式邮件
     * @throws Exception
     */
    @Test
    public void testHtmlMail() throws Exception{
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("18968249632@163.com","test html mail",content);
    }


    /**
     * 发送附件形式的邮件
     */
    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\Users\\Administrator\\Desktop\\test.png";
        mailService.sendAttachmentsMail("18968249632@163.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    /**
     * 发送带有静态资源的邮件
     */
    @Test
    public void sendInlineResourceMail() {
        String rscId = "test001";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Administrator\\Desktop\\test.png";

        mailService.sendInlineResourceMail("18968249632@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    //解析模板并发送
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context(new Locale(""));
        context.setVariable("name", "赵小刀");
        String emailContent = this.templateEngine.process(EMAIL_INLINEIMAGE_TEMPLATE_NAME, context);

        mailService.sendHtmlMail("18768104140@163.com","主题：这是模板邮件",emailContent);
    }
}
