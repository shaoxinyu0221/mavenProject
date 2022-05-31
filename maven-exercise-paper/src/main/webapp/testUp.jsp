<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/31
  Time: 下午 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试文件上传</title>
</head>
<body>
    <form action="upload" method="post" enctype="multipart/form-data" onsubmit="checkForm()">
        <input type="text" name="account" /> <br><br>

        <input type="file" name="pic" id="file"/> <br><br>

        <input type="submit" value="提交" />
        <input type="button" value="测试" onclick="fun()"/>
    </form>

    <script>
        function checkForm(){
            let fileName = document.getElementById("file").value;
            //判断文件是否为空
            if (fileName == ""){
                alert("文件名不能为空")
                return false;
            }
            //判断文件格式
            let suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            if(suffix.toLowerCase()!="jpg" && suffix.toLowerCase()!="png" && suffix.toLowerCase()!="gif"){
                alert("文件格式不符,请重新上传")
                return false;
            }
            //判断文件大小
            let size = fileName.files[0].size;
            let maxSize = 2097152;
            if(size < maxSize){
                alert("文件大小超出限制")
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
