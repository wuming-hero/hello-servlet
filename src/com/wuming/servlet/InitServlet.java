package com.wuming.servlet;


import com.wuming.implement.GradeImplement;
import com.wuming.pojo.Grade;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    GradeImplement gradeImplement = new GradeImplement();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        /*request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");*/
    }

    public void init(ServletConfig config)
            throws ServletException {

        ArrayList<Grade> grades = gradeImplement.queryGrade("");
        //System.out.println(grades.size());
        ServletContext app = config.getServletContext();
        app.setAttribute("grades", grades);
        //this.getServletContext().setAttribute("grades",gradeImplement.queryGrade(""));
        String[] hobby = {"读书", "篮球", "羽毛球"};
        app.setAttribute("hobby", hobby);

    }

}
