<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 上午 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是一个登录页面</title>
</head>
<body>
<h1>欢迎来到登录页面</h1>
<form action="login" method="post" onsubmit="return login()">
    <input type="text" name="username" id="username" placeholder="请输入用户名" /><br>
    <input type="password" name="password" id="password" placeholder="请输入密码" /><br>
    <input type="submit" value="登录" />
    <button name="register" onclick=window.open('http://localhost:80/maven-webapp/register.jsp')>点击注册</button>
    <span style="color:red" id="loginError">
        ${error}
        </span>
</form>

<script>

    function login(){
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if(username == "" || password == ""){
            alert("用户名或密码不能为空");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
