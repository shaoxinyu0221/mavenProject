<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/31
  Time: 上午 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论文更新页面</title>
    <style>
        .paperUpdate {
            width: 80%;
            text-align: center;
            border: 1px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
    <h1>欢迎来到论文修改页面</h1>

    <form action="paper" method="post" enctype="multipart/form-data">
        <input type="hidden" name="opr" value="paperModify" />
        <input type="hidden" name="updateId" value="${paper.id}" />
        <table class="paperUpdate" align="center" border="1">
            <tr>
                <td>论文标题:</td>
                <td><input type="text" name="updateTitle" value="${paper.title}"/></td>
            </tr>
            <tr>
                <td>论文摘要:</td>
                <td><textarea name="updateSummary">${paper.paperSummary}</textarea></td>
            </tr>
            <tr>
                <td>论文类型:</td>
                <td>
                    <select name="updateTypeId">
                        <c:forEach var="type" items="${typeList}">
                            <option value="${type.id}"
                                <c:if test="${type.id eq paper.id}">selected</c:if>
                            >${type.pername}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>论文:</td>
                <td><input type="file" name="file" /></td>
            </tr>
            <tr>
                <td>论文状态:</td>
                <td>
                    <c:if test="${paper.status eq 'y'}">
                        <input type="radio" name="status" value="y" id="y" checked/><label for="y">正常</label>
                        <input type="radio" name="status" value="n" id="n"/><label for="n">作废</label>
                    </c:if>
                    <c:if test="${paper.status eq 'n'}">
                        <input type="radio" name="status" value="y" id="y"/><label for="y">正常</label>
                        <input type="radio" name="status" value="n" id="n" checked/><label for="n">作废</label>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="保存" />
                    <span>${error}</span>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
