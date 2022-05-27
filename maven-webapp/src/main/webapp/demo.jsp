<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/27
  Time: 下午 3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计算两数之积</title>
</head>
<body>
    <form action="calculate" method="post" onsubmit="return checkForm()" name="myForm">
        <span>请输入第一个数字</span><input type="text" name="num1"><br>
        <span>请输入第二个数字</span><input type="text" name="num2"><br>
        <input type="submit" value="计算" onclick="fun()">
    </form>

    <script>
        function checkForm(){
            var num1 = document.myForm.num2.value;
            var num2 = document.myForm.num1.value;
            if(num1 == "" || num2 == ""){
                alert("输入不能为空")
                return false;
            }
            if(isNaN(num1) || isNaN(num2)){
                alert("输入的不是数字")
                return false;
            }
            return true;
        }
    </script>

</body>
</html>
