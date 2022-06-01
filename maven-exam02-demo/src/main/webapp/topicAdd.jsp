<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/1
  Time: 下午 7:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>话题新增页面</title>
    <style>
        table{
            text-align: center;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
    <form action="topic" method="post" onsubmit="return checkSub()">
        <input type="hidden" name="opr" value="topicAdd">
        <table border="1">
            <tr>
                <th>学生姓名:</th>
                <td>
                    <select name="studentId" id="studentId">
                        <option value="">请选择</option>
                        <c:forEach var="student" items="${studentInfo}">
                            <option value="${student.id}">${student.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <th>指导老师:</th>
                <td>
                    <select name="teacherId" id="teacherId">
                        <option value="">请选择</option>
                        <c:forEach var="teacher" items="${teacherInfo}">
                            <option value="${teacher.id}">${teacher.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <th>课题名称:</th>
                <td><input type="text" name="topicname" id="topicname"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="添加课题" />
                </td>
            </tr>
        </table>
    </form>
</body>
<p>${error}</p>
<script>
    function checkSub(){
        let studentId = document.getElementById("studentId").value;
        let teacherId = document.getElementById("teacherId").value;
        let topicname = document.getElementById("topicname").value;
        if (studentId==""||teacherId==""||topicname==""){
            alert("不能为空")
            return false;
        }
        return true;
    }
</script>
</html>
