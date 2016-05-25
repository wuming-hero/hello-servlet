<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询班级信息</title>
</head>
<body>
<form action="/GradeServlet" method="post">
    关键字
    <input type="text" name="gname">
    <input type="submit" value="提交">
    <input type="hidden" name="status" value="queryGrade"> <!-- 加个隐藏字段,用来后台判断执行哪个函数 -->
</form>

<table border="1">
    <tr>
        <td>编号</td>
        <td>主键</td>
        <td>班级</td>
        <td>更新</td>
        <td>删除</td>
    </tr>
    <c:forEach items="${requestScope.grades}" var="grade" varStatus="num">
        <tr>
            <td>${num.count}</td>
            <td>${grade.gid}</td>   <!-- jstl标签中的输出简写方式 -->
            <td><c:out value="${grade.gname }"/></td>   <!-- jstl标签中的输出完整写法 -->
            <td><c:url value="/template/student/updateGrade.jsp" var="update">
                <c:param name="gid" value="${grade.gid }"/>
                <c:param name="gname" value="${grade.gname }"/>
            </c:url>
                <a href="${update}">更新</a>
            </td>
            <td>
                <c:url value="GradeServlet" var="delete">
                    <c:param name="gid" value="${grade.gid }"/>
                    <c:param name="status" value="deleteGrade"></c:param>
                </c:url>
                <a href="${delete}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>