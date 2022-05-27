<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/27
  Time: 下午 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="hello?name=jack" method="get">
        输入姓名:<input name="info" type="text"><br>
        选择爱好:
        <input type="checkbox" name="hobby" value="学习">学习
        <input type="checkbox" name="hobby" value="唱歌">唱歌
        <input type="checkbox" name="hobby" value="玩游戏">玩游戏
        <br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
