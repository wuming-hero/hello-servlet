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
   name == <servlet-name>
   urlPatterns == <url-pattern>,
   loadOnStartup == <load-on-startup>
   initParam == <init-param>
   name == <param-name>
   value == <param-value>
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
