package com.example.springbootAll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: sea
 * @Description: session配置
 *
 * 在使用spring boot做负载均衡的时候，多个app之间的session应该要保持一致
 * 不然的话负载到不同的app时候，在一个app登录之后，而访问到另外一台服务器的时候，session丢失。
 *
 * 做了如下配置之后: 登录系统在不同的app之间跳转的时候，session都是一致
 * @Date: 20:21 2017/8/8
 */
@Configuration
//maxInactiveIntervalInSeconds设置session失效时间 使用Redis Session之后，原Boot的server.session.timeout属性不再生效
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
    
}
