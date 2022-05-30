<%@ page import="com.demo.entity.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <table>
        <tr>
            <td>编号</td><td>姓名</td><td>密码</td>
        </tr>
        <c:forEach var="user" items="${userlist}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
