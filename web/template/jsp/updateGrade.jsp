<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新班级信息</title>
</head>
<body>
更新班级信息

<form action="/Jsp/GradeServlet" method="post">
	班级:<input type="text" name="gname" value="${param.gname}"><br/>
	<input type="submit" value="提交">
	<input type="hidden" name="status" value="updateGrade">
	<input type="hidden" name="gid" value="${param.gid }">
</form>
</body>
</html>