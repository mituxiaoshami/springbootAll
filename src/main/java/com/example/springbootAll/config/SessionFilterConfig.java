package com.example.springbootAll.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。
 *
 * Pattern： 创建一个正则表达式，也可以说是创建一个匹配模式
 * Matcher： 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: sea
 * @Description:
 * @Date: 14:46 2017/8/5
 */
@Configuration
public class SessionFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<>();

    /**
     * 封装，不需要过滤的静态资源 这里判断的是静态资源路径
     * 存在在下面的文件夹下的资源 都过滤
     * @return
     */
    private String staticPassUrl = "css.*,frame.*,image.*,js.*,login.*";
    private String[] staticPassUrls;

    @Bean(name = "sessionFilter")
    public Filter sessionFilter() {
        return new SessionFilter();
    }

    @Bean
    //@Order(Integer.MAX_VALUE)
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        //当有多个过滤器时 按照参数的大小，从小到大的顺序来执行过滤器 (无论使用注解，还是这种情况都可以)
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

    public class SessionFilter implements Filter{

        public void init(FilterConfig config) {

            /**
             * 将要过滤的静态资源类型添加到过滤列表中
             */
            staticPassUrls = staticPassUrl.split(",");
            for (String url : staticPassUrls) {
                //创建截取出来的静态资源的字符串的正则表达式
                Pattern pattern = Pattern.compile(url);
                patterns.add(pattern);
            }
        }

        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse reponse = (HttpServletResponse) servletResponse;
            String url = request.getRequestURI().substring(request.getContextPath().length());
            //有的时候RequestMapping("index")，而有的时候RequestMapping("/index")
            if (url.startsWith("/") && url.length() > 1) {
                url = url.substring(1);
            }

            //如果是ico类型的图片 也是直接过滤放行
            if (url.contains(".ico")) {
                filterChain.doFilter(request,reponse);
                return;
            }

            if (isInclude(url)) {
                filterChain.doFilter(request,reponse);
                return;
            }else {
                logger.info("this is MyFilter,url :"+request.getRequestURI());
                HttpSession session = request.getSession();
                if (session.getAttribute("") != null) {
                    //session存在
                    filterChain.doFilter(request,reponse);
                    return;
                }else {
                    //session不存在 准备跳转失败
                    /*RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request,reponse);*/
                    filterChain.doFilter(request,reponse);
                    return;
                }
            }
        }

        public void destroy() {

        }

    }

    /**
     * @Author: sea
     * @Description: 是否需要过滤
     * @Date: 15:20 2017/8/5
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            /*Pattern pattern = Pattern.compile("Java");
            String test1 = "Java";
            String test2 = "Java1234";
            Matcher matcher = pattern.matcher(test1);
            System.out.println(matcher.matches());//返回true
            matcher = pattern.matcher(test2);
            System.out.println(matcher.matches());//返回false*/

           //过渡到Matcher类,Pattern类中的matcher(CharSequence input)会返回一个Matcher对象
            Matcher matcher = pattern.matcher(url);
            //matches()用于全字符串匹配
        if (matcher.matches()) {
            return true;
        }
        }
        return false;
    }
}
