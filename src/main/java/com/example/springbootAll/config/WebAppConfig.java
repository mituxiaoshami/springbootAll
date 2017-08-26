package com.example.springbootAll.config;

import com.example.springbootAll.handlerInterceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: sea
 * @Description:
 * @Date: 19:40 2017/8/23
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

    /**
     * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //pictures 文件夹中的test.jpg 图片的地址为 http://localhost:8080/pictures/test.jpg
        //其中 addResourceLocations 的参数是动参，可以这样写 addResourceLocations(“classpath:/pictures1/”, “classpath:/pictures2/”, “classpath:/pictures3/”);
        registry.addResourceHandler("/pictures/**").addResourceLocations("classpath:/pictures/");
        //如果要指定一个绝对路径的文件夹(例如:D:/data/files) 同样可以配置多个位置，注意路径写法需要加上file:
        registry.addResourceHandler("/files/**").addResourceLocations("file:D:/data/files");
        super.addResourceHandlers(registry);
    }

    /**
     * 添加过滤链
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
