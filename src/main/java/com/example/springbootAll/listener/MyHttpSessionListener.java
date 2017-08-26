package com.example.springbootAll.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author: sea
 * @Description: 监听session的创建与销毁
 * @Date: 14:03 2017/8/24
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

    private static Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("Session 被创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info("Session 被销毁");
    }

}
