package com.example.springbootAll.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: sea
 * @Description: Spring boot 的主 Servlet 为 DispatcherServlet，其默认的url-pattern为“/”
 *                 在spring boot中添加自己的Servlet有两种方法，代码注册Servlet和注解自动注册（Filter和Listener也是如此）。
 * @Date: 9:58 2017/8/24
 */
// 这个可以不需要加
@WebServlet(urlPatterns = "/servlet/*",description = "Servlet的说明")
public class MyServlet extends HttpServlet{

    private static Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private static final Long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(">>>>>>>>>>doGet()<<<<<<<<<<<");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       logger.info(">>>>>>>>>>doPost()<<<<<<<<<<<");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>This is MyServlet</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
