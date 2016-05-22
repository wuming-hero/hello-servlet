package com.wuming.servlet3;

import com.wuming.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by wuming on 16/5/22.
 */

/**
 * Servlet 3.0 特性
 *  1.Servlet、Filter、Listener无需在web.xml中进行配置，可以通过Annotation进行配置；
 *  2.模块化编程，即将各个Servlet模块化，将配置文件也分开配置。
 *  3.Servlet异步处理，应对复杂业务处理；
 *  4.异步Listener，对于异步处理的创建、完成等进行监听；
 *  5. 文件上传API简化；
 */

/**
 *  web.xml 配置方式:
 *  name == <servlet-name>
 *  urlPatterns == <url-pattern>,
 *  loadOnStartup == <load-on-startup>
 *  initParam == <init-param>
 *  name == <param-name>
 *  value == <param-value>
 */

/**
 *  注解方式;
 * Servlet3.0支持使用注解配置Servlet。我们只需在Servlet对应的类上使用@WebServlet进行标注，
 * 我们就可以访问到该Servlet了，而不需要再在web.xml文件中进行配置。
 * @WebServlet的urlPatterns和value属性都可以用来表示Servlet的部署路径，它们都是对应的一个数组。
 * 如果只部署一个路径可直接写成一个字符串
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"}, loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = "name", value = "wuming"),
                @WebInitParam(name = "age", value = "26")
        })
public class HelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletConfig config = getServletConfig();
        String name = config.getInitParameter("name");
        String age = config.getInitParameter("age");

        PrintWriter out = response.getWriter();
        out.println("欢迎来到"+ name +" 来到 servlet 3.0 时代~<br><br>");
        out.println("现在时间是" + DateUtil.DateTimeToStr(new Date()));
        out.println("您已经" + age + "岁了");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
