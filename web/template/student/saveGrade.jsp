<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存班级信息</title>
</head>
<body>
<form action="/GradeServlet" method="post">
	班级:<input type="text" name="gname"><br/>
	<input type="submit" value="提交">
	<input type="hidden" name="status" value="saveGrade">
</form>
</body>
</html>