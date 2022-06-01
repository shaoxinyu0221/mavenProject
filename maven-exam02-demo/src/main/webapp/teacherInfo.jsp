<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/1
  Time: 下午 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息表</title>
    <style>
        table{
            text-align: center;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>老师信息</h1>
    <table border="1">
        <tr>
            <th>老师id</th>
            <td>${teacher.id}</td>
        </tr>
        <tr>
            <th>老师姓名</th>
            <td>${teacher.name}</td>
        </tr>
        <tr>
            <th>老师电话</th>
            <td>${teacher.telephone}</td>
        </tr>
    </table>
<hr>
<h1>所指导的学生</h1>
<table border="1">
    <tr>
        <th>学生id</th>
        <th>学生姓名</th>
        <th>学生电话</th>
        <th>课题名称</th>
    </tr>
    <c:forEach var="student" items="${studentList}">
        <tr>
            <th>${student.id}</th>
            <th>${student.name}</th>
            <th>${student.telephone}</th>
            <th>${student.topicName}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
