package com.wuming.servlet3; /**
 * Created by wuming on 16/5/22.
 */

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 *  异步监听器用来监听异步处理事件；即“三”中讲到的知识点；
 *  此监听器类似于ServletContextListener的机制；
 *  只需要实现AsyncListener接口即可；
 *
 *  使用:在Servlet异步处理处添加：
 *  actx.addListener(new MyListener())；就可以添加监听器，每当异步处理完成时就会触发onComplete()事件，输出Complete；
 */

@WebListener()
public class MyAsyncListener implements AsyncListener {


    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("-----------------------Complete");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}
