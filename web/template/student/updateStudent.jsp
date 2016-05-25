<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新学生信息</title>
    </head>

    <body>
        <c:forTokens items="${param.hobby}" delims="-" var="myHobby">
            <c:out value="${jj}"></c:out>
        </c:forTokens>更新学生信息

        <form action="/StudentServlet" method="post">
            <%
                String str = request.getParameter("name");
                request.setAttribute("str", str);
            %>
            <%
                String str1 = request.getParameter("hobby");
                request.setAttribute("str1", str1);
            %>
            姓名: <input type="text" name="name"
                      value="${str}<%-- <%=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8")%> --%>"><br/>
            密码: <input type="password" name="password" value="${param.password }"><br/>
            爱好: ${str1}
            <br>
            <c:forEach items="${applicationScope.hobby}" var="hobby">
                <!-- 每次让默认的爱好与被选的爱好比较,如果匹配成功则输出的爱好被选中 -->
                <c:set var="checked"/>
                <c:forTokens items="${str1}" delims="-" var="myHobby">
                    <!-- 判断系统输出的与所选的是否有匹配的字符串 -->
                    <c:if test="${hobby==myHobby}">
                        <c:set value="checked='checked'" var="checked"/>
                    </c:if>
                </c:forTokens>

                ${hobby}<input type="checkbox" value="${hobby}"  ${checked} />
            </c:forEach>
            <br/>

            班级:
            <select name="gid">
                <c:forEach items="${applicationScope.grades}" var="grade">
                    <c:choose>
                        <c:when test="${grade.gid==param.gid}">
                            <option value="${grade.gid}" selected="selected">
                                    ${grade.gname}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${grade.gid}">
                                    ${grade.gname}
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <input type="hidden" name="status" value="updateStudent">
            <input type="hidden" name="id" value="${param.id }">
            <br>
            <br>
            <input type="submit" value="提交">
        </form>
    </body>
</html>