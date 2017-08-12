package com.example.springbootAll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: sea
 * @Description: session配置
 * @Date: 20:21 2017/8/8
 */
@Configuration
//maxInactiveIntervalInSeconds设置session失效时间 使用Redis Session之后，原Boot的server.session.timeout属性不再生效
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
    
}
