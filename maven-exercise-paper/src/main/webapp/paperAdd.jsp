<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/30
  Time: 下午 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            list-style: none;
            text-decoration: none;
        }
        .header {
            height:50px;
            padding-left: 20px;
            line-height: 50px;
            background-color: grey;
        }
        .paperAdd {
            width: 80%;
            text-align: center;
            border: 1px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>欢迎来到论文添加页面</h1>
    <div class="header">
        <p>您现在的位置:论文管理 &nbsp;&nbsp;/&nbsp;&nbsp; <a href="paper?opr=paperAdd">论文添加</a></p>
    </div>

    <form method="post" action="paper?opr=addSuccess" enctype="multipart/form-data" onsubmit="return checkForm()">
        <table class="paperAdd" align="center" border="1">
            <tr>
                <td>论文标题:</td>
                <td><input type="text" name="addtitle" id="title"/></td>
            </tr>
            <tr>
                <td>论文摘要:</td>
                <td><textarea name="addpapersummary" id="summary"></textarea></td>
            </tr>
            <tr>
                <td>论文类型:</td>
                <td>
                    <select name="addtypeId">
                        <option value="">---请选择---</option>
                        <c:forEach var="types" items="${paperTypes}">
                            <option value="${types.id}">${types.pername}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>论文:</td>
                <td><input type="file" name="addfile" id="file"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="保存" />
                    <span style="color:red">${error}</span>
                </td>
            </tr>
        </table>
    </form>


    <script>
        function checkForm(){

        }
    </script>
</body>
</html>
