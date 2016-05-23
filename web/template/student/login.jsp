<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>jsp示例</title>
</head>
<body>
<%-- Now time is: <%=new java.util.Date()%> --%>
<form name="loginForm" method="post" action="/AdminServlet">
    <input type="hidden" name="status" value="login">

    用户名:
        <input type="text" name="aname" value="${requestScope.aname}"><br><br>
    密&nbsp;&nbsp;码: ${requestScope.apass}
        <input type="password" name="apass" value="${requestScope.apass}"><br>

    <%--${requestScope.error}--%>

    <%if (request.getAttribute("error") != null){ %>
    <span style="color: #ff0000;">
        <%=request.getAttribute("error")%>
    </span>
    <%}%>
    <br>

    <select name="auto">
        <option value='0'>不自动填充</option>
        <option value='7'>一周</option>
        <option value='30'>一月</option>
    </select>
    <br/>
    <input type="Submit" name="Submit" value="确定">

</form>
</body>
</html>