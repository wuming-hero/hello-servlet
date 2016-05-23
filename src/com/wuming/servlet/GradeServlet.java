package com.wuming.servlet;


import com.wuming.implement.GradeImplement;
import com.wuming.pojo.Grade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class GradeServlet
 */
public class GradeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    GradeImplement gradeImplement = new GradeImplement();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String status = request.getParameter("status");
        if (status.equals("saveGrade")) {
            Grade grade = new Grade();
            grade.setGname(request.getParameter("gname"));
            gradeImplement.saveGrade(grade);
            //一行代码把InitServlet里的数据更新,于是saveStudent.jsp中加载的班级信息就是及时更新!!
            this.getServletContext().setAttribute("grades", gradeImplement.queryGrade(""));
        } else if (status.equals("queryGrade")) {
            String gname = request.getParameter("gname");
            request.getSession().setAttribute("gname", gname);  //将传过来的值放到自己session变量里
            ArrayList<Grade> grades = gradeImplement.queryGrade(gname);
            request.setAttribute("grades", grades);//自己查询的数据放到request对象中!
            //此跳转是内部跳转,只能跳转到工程内部,中用跳转页面之间的request数据的共享!!所以只有数据存在request情况下才有内部跳转
            request.getRequestDispatcher("/template/student/queryGrade.jsp").forward(request, response);
        } else if (status.equals("updateGrade")) {
            Grade grade = new Grade();
            grade.setGid(Integer.parseInt(request.getParameter("gid")));
            grade.setGname(request.getParameter("gname"));
            gradeImplement.updateGrade(grade);
            //更新到app里,供saveStudent.jsp下的班级信息及时更新
            this.getServletContext().setAttribute("grades", gradeImplement.queryGrade(""));
            //如果servlet与目标页面没有数据的传递,可以使用外部跳转,以下方法为外部跳转!!可以跳到工程外部,比如说 百度...
            //response.sendRedirect("/template/student/queryGrade.jsp");
            String gname = request.getSession().getAttribute("gname").toString();
            ArrayList<Grade> grades = gradeImplement.queryGrade(gname); //只查询之前的session!
            //ArrayList<Grade> grades=gradeImplement.queryGrade(""); //直接这样重新查询下即可,查询全部,
            request.setAttribute("grades", grades);
            request.getRequestDispatcher("/template/student/queryGrade.jsp").forward(request, response);
        } else if (status.equals("deleteGrade")) {
            int gid = Integer.parseInt(request.getParameter("gid"));
            gradeImplement.deleteGrade(gid);
            //用getAttribute得到自己的session变量的值的对象,再用toString转换为字符串
            String gname = request.getSession().getAttribute("gname").toString();
            ArrayList<Grade> grades = gradeImplement.queryGrade(gname); //只查询之前的session!
            //ArrayList<Grade> grades=gradeImplement.queryGrade(""); //直接这样重新查询下即可,查询全部,
            request.setAttribute("grades", grades);
            request.getRequestDispatcher("/template/student/queryGrade.jsp").forward(request, response);
        }
    }
}