<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 下午 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>scope02.jsp</title>
</head>
<body>
    <h2>这是scope02.jsp</h2>
    作用域:
    <%=pageContext.getAttribute("page")%>
    <%=request.getAttribute("request")%>
    <%=session.getAttribute("session")%>
    <%=application.getAttribute("application")%>
</body>
</html>
