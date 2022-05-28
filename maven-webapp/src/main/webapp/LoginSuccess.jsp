<%@ page import="com.demo.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 上午 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<User> userlist = (List<User>) request.getAttribute("userlist");
%>
    <table>
        <tr>
            <td>编号</td><td>姓名</td><td>密码</td>
        </tr>
        <% for(User user : userlist){ %>

        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getUsername()%></td>
            <td><%=user.getPassword()%></td>
        </tr>

        <% } %>
    </table>
</body>
</html>
