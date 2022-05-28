<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/28
  Time: 下午 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL语法</title>
</head>
<body>
    <%
        pageContext.setAttribute("name","张三");
        //剩下三个作用域和Servlet作用域相同
        request.setAttribute("name","李四");
        session.setAttribute("name","王五");
        application.setAttribute("name","刘六");
    %>

    <h2>EL语法,取代jsp中的表达式</h2>
    el语法的取的值必须是用在四种作用域中存储的值<br>
    用EL表达式取值:${con_name}<br>
    如果在变量名相同时,采用就近原则,从小到大搜索,如果想要访问其他变量,
    可以在变量名前加作用域前缀<br>
    用EL取同名的默认值:${name}<br>
    用EL取同名的其他值:${requestScope.name}<br>
    如果想要输出静态内容,可以用双引号<br>
    用EL输出静态内容:${"name"}<br>
</body>
</html>
