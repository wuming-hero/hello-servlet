<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存学生信息</title>
</head>
<body>
<form action="/Jsp/StudentServlet" method="post">
	姓名:<input type="text" name="name"><br/>
	密码:<input type="password" name="password"><br/>
	爱好:<c:forEach items="${applicationScope.hobby}" var="hobby">
		${hobby}<input type="checkbox" value="${hobby}"/>
	</c:forEach>
	<br/>
   	班级:<select name="gid" >
   		<c:forEach items="${applicationScope.grades}" var="grade">
   			<option value="${grade.gid }">${grade.gname}</option>
   		</c:forEach>
   	</select>
	<input type="submit" value="提交">
	<input type="hidden" name="status" value="saveStudent">
</form>
</body>
</html>