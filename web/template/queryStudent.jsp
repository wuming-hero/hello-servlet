<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
         pageEncoding="UTF-8" %>
<%@ page import="com.wuming.pojo.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>学生列表</title>
    </head>
    <body>
        <form action="/StudentServlet" method="post">
            关键字
            <input type="hidden" name="status" value="queryStudent">
            <input type="text" name="name">
            <input type="submit" value="提交">
            <a href="/saveStudent.jsp">添加</a>
        </form>

        <table border="1">
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>密码</td>
                <td>爱好</td>
                <td>更新</td>
                <td>删除</td>
            </tr>
            <%
                ArrayList<Student> students = (ArrayList) request.getAttribute("students");
                if (students != null) {
                    for (int i = 0; i < students.size(); i++) {
            %>
            <tr>
                <td><%=students.get(i).getId()%>
                </td>
                <td><%=students.get(i).getName()%>
                </td>
                <td><%=students.get(i).getPassword()%>
                </td>
                <td><%=students.get(i).getHobby()%>
                </td>
                <td>
                    <a href="/updateStudent.jsp?id=<%=students.get(i).getId()%>&name=<%=students.get(i).getName()%>&password=<%=students.get(i).getPassword()%>&status=updateStudent">更新</a>
                </td>
                <td><a href="/StudentServlet?id=<%=students.get(i).getId()%>&status=deleteStudent">删除</a></td>

            </tr>
            <%
                    }
                }
            %>

        </table>
    </body>
</html>