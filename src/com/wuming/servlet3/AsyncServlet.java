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
 * Servlet在MVC中作为控制器，控制器负责分发任务给MODEL完成，然后把结果交给JSP显示；
 * 而如果有许多MODEL，其中有一个MODEL处理时间很长，则会导致整个页面的显示很慢；
 * 异步处理关键点：将复杂业务处理另外开一个线程，而Servlet将执行好的业务先送往jsp输出，等到耗时业务做完后再送往JSP页面；
 * 一句话：先显示一部分，再显示一部分；
 * <p>
 * 异步处理Servlet的注意点是：
 * 首先我们必须指定@WebServlet的asyncSupported属性为true（默认是false），同时在它之前的Filter
 * 的asyncSupported属性也必须是true，否则传递过来的request就是不支持异步调用的。
 */
@WebServlet(name = "AsyncServlet", urlPatterns = {"/asyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        out.println("异步之前输出的内容。<br><br>");
        out.flush();

        //开始异步调用，获取对应的AsyncContext。
        final AsyncContext asyncContext = request.startAsync();
        //设置超时时间，当超时之后程序会尝试重新执行异步任务，即我们新起的线程。
        asyncContext.setTimeout(10 * 1000L);
        //新起线程开始异步调用，start方法不是阻塞式的，它会新起一个线程来启动Runnable接口，之后主程序会继续执行
        asyncContext.start(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000L);
                    out.println("异步调用之后输出的内容。");
                    out.flush();
                    // 调用异步监听器
                    // actx.addListener(new MyAsyncListener());
                    //异步调用完成，如果异步调用完成后不调用complete()方法的话，异步调用的结果需要等到设置的超时
                    //时间过了之后才能返回到客户端。
                    asyncContext.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        out.println("可能在异步调用前输出，也可能在异步调用之后输出，因为异步调用会新起一个线程。<br><br>");
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
            // 调用异步监听器
            // actx.addListener(new MyAsyncListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
