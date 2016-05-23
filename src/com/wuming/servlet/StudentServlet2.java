package com.wuming.servlet;


import com.wuming.implement.StudentImplement;
import com.wuming.pojo.Student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //定义全局变量name,因为在查询中用到,而在删除后还要把删除后的数据显示出来,所以删除后也要做查询操作,也要用到name!!
    String name = null;

    public StudentServlet2() {
        super();
    }

    /* Ser 销毁的时候调用此方法 */
    public void destroy() {
        super.destroy();
        //System.out.println("----destory----"); //servlet在服务器(容器)关闭时被销毁!!
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("---doPost---");
        //servlet是mvc模式的controller层,负责得到数据和调用业务层去实现
        StudentImplement studentImplement = new StudentImplement();

        // jsp请求后台的数据全部封装到request对象中,request对象的默认编码也是Iso-8859-1(英文编码,不支持中文),所以如下设置后,便可以传输中文!
        request.setCharacterEncoding("UTF-8");

        System.out.println("name: " + request.getParameter("name"));

        //第一个jsp页面都向controller层提交数据,所以要在每一个提交页面中加了一个隐藏字段:status,用来判断是哪个jsp发来的说请求!
        String status = request.getParameter("status");

        if ("saveStudent".equals(status)) {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setPassword(request.getParameter("password"));
            //爱好传过来的不只一个值,是一个数组,所以要用getParameterValues,得到数组后要转为字符串才能向数据库插入,所以要用到"-"将数据串成字符串,具体实现方法为业务层的joinString();
            String[] hobbyList = request.getParameterValues("hobby");
            String hobby = hobbyList == null ? "" : studentImplement.joinString(hobbyList);
            student.setHobby(hobby);
            //调用业务层去实现
            studentImplement.saveStudent(student);
        } else if ("queryStudent".equals(status)) {
            name = request.getParameter("name");
            ArrayList<Student> students = studentImplement.queryStudent(name, 1);
            //setAttribute(),此方法有两个参数,第一个参数为名字,第二个参数为对象,相当于一个名字对应一个集合
            request.getSession().setAttribute("name", name);  //将传过来的值放到自己session变量里
            // 要把当前页保存到session中,因为后面会反复的用到
            request.getSession().setAttribute("currentPage", 1);
            // 把总页数也保存到session中,因为后面会反复的用到
            request.getSession().setAttribute("countPage", studentImplement.countPage(name));
            request.setAttribute("students", students);
            //页面之间跳转:servlet跳到jsp显示出我们查出的数据,但此跳转为内部跳转,不能跳转到此工程外!!!
            RequestDispatcher dispatcher = request.getRequestDispatcher("/template/student/queryStudent.jsp");
            dispatcher.forward(request, response);
            return;
        } else if ("deleteStudent".equals(status)) {
            int id = Integer.parseInt(request.getParameter("id"));
            studentImplement.deleteStudent(id);
            //删除后数据发生改变,要重新去数据库中查询并显示,同上面的查询代码一样
            ArrayList<Student> students = studentImplement.queryStudent(name);
            request.setAttribute("students", students);
        } else if ("updateStudent".equals(status)) {
            Student student = new Student();
            student.setId(Integer.parseInt(request.getParameter("id")));
            student.setName(request.getParameter("name"));
            student.setPassword(request.getParameter("password"));
            String[] hobbyList = request.getParameterValues("hobby");
            String hobby = hobbyList == null ? "" : studentImplement.joinString(hobbyList);
            student.setHobby(hobby);
            studentImplement.updateStudent(student);
        } else {
            request.setAttribute("error", "404 您忘记传入 status 参数了~");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/template/student/queryStudent.jsp").forward(request, response);
    }

}
