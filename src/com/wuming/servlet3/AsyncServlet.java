package com.wuming.servlet3;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wuming on 16/5/22.
 */

/**
 *  Servlet在MVC中作为控制器，控制器负责分发任务给MODEL完成，然后把结果交给JSP显示；
 *  而如果有许多MODEL，其中有一个MODEL处理时间很长，则会导致整个页面的显示很慢；
 *  异步处理关键点：将复杂业务处理另外开一个线程，而Servlet将执行好的业务先送往jsp输出，等到耗时业务做完后再送往JSP页面；
 *  一句话：先显示一部分，再显示一部分；
 *
 *  异步处理Servlet的注意点是：
 *  需要在Annotation中注明 asyncSupported=true;
 */
@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("====页面开始====<hr />");
        AsyncContext actx = request.startAsync();
        actx.setTimeout(30 * 3000);
        actx.start(new MyThread(actx));
        out.println("====页面结束====<hr />");
        out.println("</body>");
        out.println("</html>");
        out.flush();

    }
}

class MyThread implements Runnable {
    private AsyncContext actx;

    public MyThread(AsyncContext actx) {
        this.actx = actx;
    }

    public void run() {
        try {
            Thread.sleep(5 * 1000); //消耗5秒
            actx.dispatch("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
