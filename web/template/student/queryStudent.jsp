<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- jsp 2.4及以后版本需要使用http://java.sun.com/jstl/core_rt代替-->
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查询学生信息</title>
    </head>

    <body>
        <form action="/StudentServlet" method="post">
            关键字
            <input type="text" name="name" value="${sessionScope.name}">
            <input type="submit" value="提交">
            <input type="hidden" name="status" value="queryStudent"> <!-- 加个隐藏字段,用来后台判断执行哪个函数 -->
        </form>

        <table border="1">
            <tr>
                <td>编号</td>
                <td>主键</td>
                <td>姓名</td>
                <td>密码</td>
                <td>爱好</td>
                <td>班级编号</td>
                <td>班级名</td>
                <td>更新</td>
                <td>删除</td>
            </tr>
            <c:forEach items="${requestScope.students}" var="student" varStatus="num">
                <tr>
                    <td>${num.count}</td>
                    <td>${student.id}</td>   <!-- jstl标签中的输出简写方式 -->
                    <td><c:out value="${student.name }"/></td>   <!-- jstl标签中的输出完整写法 -->
                    <td><c:out value="${student.password }"/></td>
                    <td><c:out value="${student.hobby }"/></td>
                    <td><c:out value="${student.grade.gid}"></c:out></td>
                    <td><c:out value="${student.grade.gname}"></c:out></td>
                    <td><c:url value="/template/student/updateStudent.jsp" var="update">
                        <c:param name="id" value="${student.id }"/>
                        <c:param name="name" value="${student.name }"/>
                        <c:param name="password" value="${student.password }"/>
                        <c:param name="hobby" value="${student.hobby }"/>
                        <c:param name="gid" value="${student.grade.gid }"/>
                    </c:url>
                        <a href="${update }">更新</a>
                    </td>
                    <td>
                        <c:url value="StudentServlet" var="delete">
                            <c:param name="id" value="${student.id }"/>
                            <c:param name="status" value="deleteStudent"></c:param>
                        </c:url>
                        <a href="${delete}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/StudentServlet?status=splitPage&currentPage=1">首页</a>
        <c:choose>
            <c:when test="${sessionScope.currentPage==1}">
                上一页
            </c:when>
            <c:otherwise>
                <a href="/StudentServlet?status=splitPage&currentPage=${sessionScope.currentPage-1}">上一页</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope.currentPage==sessionScope.countPage}">
                下一页
            </c:when>
            <c:otherwise>
                <a href="/StudentServlet?status=splitPage&currentPage=${sessionScope.currentPage+1}">下一页 </a>
            </c:otherwise>
        </c:choose>
        <a href="/StudentServlet?status=splitPage&currentPage=${sessionScope.countPage}">尾页 </a>
        <script type="text/javascript">
            function changePage() {
                var currentPage = document.getElementById("currentPage").value;
                window.open("/StudentServlet?status=splitPage&currentPage=" + currentPage, "_self");
            }
        </script>
        <select id="currentPage" onchange="changePage()">
            <c:forEach begin="1" end="${sessionScope.countPage}" step="1" var="num">
                <c:choose>
                    <c:when test="${num==sessionScope.currentPage}">
                        <option value="${num}" selected="selected">${num}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${num}">${num}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </body>
</html>