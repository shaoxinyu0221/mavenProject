<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/1
  Time: 上午 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录页面</title>
</head>
<body>
  <h1>欢迎来到管理员登录页面</h1>
  <form action="book" method="post" onsubmit="return checkLogin()">
      <input type="hidden" name="opr" value="login"/>
      <input type="text" name="username" id="username"/>
      <input type="password" name="password" id="password"/>
      <input type="submit" value="登录" />
  </form>


 <script>
     function checkLogin(){
         let username = document.getElementById("username").value;
         let password = document.getElementById("password").value;
         if(username==""||password==""){
             alert("用户名密码不能为空")
             return false;
         }
        return true;
     }
 </script>
</body>
</html>
