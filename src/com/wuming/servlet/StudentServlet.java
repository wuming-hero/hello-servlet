package com.wuming.servlet;


import com.wuming.implement.StudentImplement;
import com.wuming.pojo.Student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    StudentImplement studentImplement = new StudentImplement();

    public StudentServlet() {
        super();
    }

    /* Ser 销毁的时候调用此方法 */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
        //System.out.println("----destory----");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //jsp请求后台的数据全部封装到request对象中,request对象的默认编码也是Iso-8859-1(英文编码,不支持中文),所以如下设置后,便可以传输中文!
        request.setCharacterEncoding("UTF-8");
        //第一个jsp页面都向controller层提交数据,所以要在每一个提交页面中加了一个隐藏字段:status,用来判断是哪个jsp发来的说请求!
        String status = request.getParameter("status");
        //System.out.println(request.getSession());//通过此方法可以得到浏览器服务器发来的请求,同一个浏览器发来session是一样的(一直没关闭的话),不同的浏览器会不同!

        if ("saveStudent".equals(status)) {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setPassword(request.getParameter("password"));
            //爱好传过来的不只一个值,是一个数组,所以要用getParameterValues,得到数组后要转为字符串才能向数据库插入,所以要用到"-"将数据串成字符串,具体实现方法为业务层的joinString();
            String[] hobby = request.getParameterValues("hobby");
            student.setHobby(studentImplement.joinString(hobby));
            //调用业务层去实现
            studentImplement.saveStudent(student);
        } else if ("queryStudent".equals(status)) {
            String name = request.getParameter("name");  //声明局部变量
            request.getSession().setAttribute("name", name);  //将传过来的值放到自己session变量里
            // 要把当前页保存到session中,因为后面会反复的用到
            request.getSession().setAttribute("currentPage", 1);
            // 把总页数也保存到session中,因为后面会反复的用到
            System.out.println(studentImplement.countPage(name));
            request.getSession().setAttribute("countPage", studentImplement.countPage(name));
            ArrayList<Student> students = studentImplement.queryStudent(name, 1);
            //setAttribute(),此方法有两个参数,第一个参数为名字,第二个参数为对象,相当于一个名字对应一个集合
            request.setAttribute("students", students);
            //页面之间跳转:servlet跳到jsp显示出我们查出的数据,但此跳转为内部跳转,不能跳转到此工程外!!!
            //System.out.println(students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/template/student/queryStudent.jsp");
            dispatcher.forward(request, response);
        } else if ("splitPage".equals(status)) {
            //从session中拿到关键字
            String name = request.getSession().getAttribute("name").toString();
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            //更新session中的当前页
            request.getSession().setAttribute("currentPage", currentPage);
            ArrayList<Student> students = studentImplement.queryStudent(name, currentPage);

            // 如果把数据从Ser传递到视图页面中
            request.setAttribute("students", students);
            // 页面之间跳转 ser-->jsp :此跳转是内部跳转不能跳转到工程外的页面
            request.getRequestDispatcher("/template/student/queryStudent.jsp").forward(request, response);
        } else if ("deleteStudent".equals(status)) {
            int id = Integer.parseInt(request.getParameter("id"));
            studentImplement.deleteStudent(id);
            //删除后数据发生改变,要重新去数据库中查询并显示,同上面的查询代码一样
            String name = request.getSession().getAttribute("name").toString();//用getAttribute得到自己的session变量的值的对象,再用toString转换为字符串
            ArrayList<Student> students = studentImplement.queryStudent(name);
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/template/student/queryStudent.jsp");
            dispatcher.forward(request, response);
        } else if ("updateStudent".equals(status)) {
            Student student = new Student();
            student.setId(Integer.parseInt(request.getParameter("id")));
            student.setName(request.getParameter("name"));
            student.setPassword(request.getParameter("password"));
            studentImplement.updateStudent(student);
        }
    }

}
