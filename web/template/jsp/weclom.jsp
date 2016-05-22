<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>欢迎</title>
</head>
<body>
	<%request.setCharacterEncoding("UTF-8");%>
	欢迎:<%=request.getParameter("username")%>
</body>
</html>