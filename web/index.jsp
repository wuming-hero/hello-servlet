<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 16/5/22
  Time: 下午4:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
  欢迎来到 servlet 世界 <br><br>

  当前时间是 <%= new Date() %> <br><br>

  <%if (request.getAttribute("error") != null){ %>
    <span style="color: #ff0000;">
        <%=request.getAttribute("error")%>
    </span>
  <%}%>
  </body>
</html>
