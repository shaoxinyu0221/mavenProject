<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Index.jsp</title>
    <style>
        table{
            text-align: center;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h2>课题列表</h2>
<button onclick="window.open('topic?opr=gotoTopicAdd')">点击添加课题信息</button>
<table border="1">
    <tr>
        <th>学生id</th>
        <th>学生姓名</th>
        <th>学生电话</th>
        <th>课题名称</th>
        <th>老师名称</th>
        <th>操作</th>
    </tr>
    <c:forEach var="topic" items="${topicList}">
        <tr>
            <th>${topic.studentId}</th>
            <th>${topic.sname}</th>
            <th>${topic.stel}</th>
            <th>${topic.name}</th>
            <th><a href="topic?opr=queryTeacher&tid=${topic.teacherId}">${topic.tname}</a></th>
            <th><a href="topic?opr=deleteTopic&deleteId=${topic.id}" onclick="return confirm('是否确认删除')">删除</a></th>
        </tr>
    </c:forEach>
</table>
<p style="color:red">${success}</p>
</body>
</html>
