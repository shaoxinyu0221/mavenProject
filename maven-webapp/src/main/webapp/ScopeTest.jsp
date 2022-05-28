<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 下午 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用来测试Servlet作用域的页面</title>
</head>
<body>
<%
    /**
     * jsp作用域,一共四个
     */
    //页面作用域,只作用域当前页面
    pageContext.setAttribute("page","null");
    //剩下三个作用域和Servlet作用域相同
    request.setAttribute("request","request数据");
    session.setAttribute("session","session数据");
    application.setAttribute("application","application数据");
%>

<%
    if(pageContext.getAttribute("page") == null){
        request.getRequestDispatcher("/scop02.jsp").forward(request,response);
    }

%>
这是一个请求作用域:<%=request.getAttribute("req_name")%><br>
这是一个会话作用域:<%=session.getAttribute("ses_name")%><br>
这是一个全局作用域:<%=application.getAttribute("con_name")%><br>

请输入账号<input type="text" id="username" onblur="fun()">

<script>
    function fun() {
        let xhr = new XMLHttpRequest();
        xhr.open("post",)
        var username = document.getElementById("username").value;
        document.write(w)

    }

</script>


</body>
</html>
