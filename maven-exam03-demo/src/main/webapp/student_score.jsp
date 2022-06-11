<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/9
  Time: 下午 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩</title>
</head>
<body>
<h1>学生成绩</h1>

<table>
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>语文</td>
        <td>数学</td>
        <td>英语</td>
    </tr>
  <c:forEach items="${studentList}" var="score">
    <tr>
        <td>${score.student.id}</td>
        <td>${score.student.name}</td>
        <td>${score.score}</td>
        <td>${score.score}</td>
        <td>${score.score}</td>
    </tr>
  </c:forEach>
</table>

<table>
    <tr>
        <td>学科</td>
        <td>最低分</td>
        <td>最高分</td>
        <td>平均分</td>
        <td>不及格人数</td>
    </tr>
    <tr>
        <td>语文</td>
        <td>${maxChinese.score}</td>
        <td>${minChinese.score}</td>
        <td>${avgChinese}</td>
        <td>${notPassChinese}</td>
    </tr>
    <tr>
        <td>数学</td>
        <td>最低分</td>
        <td>最高分</td>
        <td>平均分</td>
        <td>${notPassMath}</td>
    </tr>
    <tr>
        <td>英语</td>
        <td>最低分</td>
        <td>最高分</td>
        <td>平均分</td>
        <td>${notPassEnglish}</td>
    </tr>
</table>
</body>
</html>
