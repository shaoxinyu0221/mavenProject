<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论文列表</title>
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
        .paperList {
            width: 80%;
            text-align: center;
            border: 1px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
    <h1>欢迎来到论文列表</h1>
    <div class="header">
        <p>您现在的位置:论文管理 &nbsp;&nbsp;/&nbsp;&nbsp; <a href="paper?opr=gotoPaperList">论文列表</a></p>
    </div>

    <div>
        <form action="paper" method="get">
            <input type="hidden" name="opr" value="gotoPaperList">
            <span>论文标题:</span><input type="text" name="title" placeholder="请输入论文名称" value="${title}"/>
            <span>论文类型:</span>
            <select name="typeId">
                <option value="">---请选择---</option>
                <c:forEach var="types" items="${paperTypes}">
                    <option value="${types.id}"
                            <c:if test="${types.id eq typeId}">selected</c:if>
                    >${types.pername}</option>
                </c:forEach>
            </select>
            <input type="submit" value="查询" />
            <input type="button" value="清空查询条件" />
            <button onclick="window.open('paper?opr=paperAdd')">添加</button>
        </form>
    </div>
    <table class="paperList" align="center" border="1">
        <tr>
            <td>序号</td>
            <td>论文标题</td>
            <td>论文类别</td>
            <td>发表时间</td>
            <td>修改时间</td>
            <td>操作</td>
        </tr>
        <c:forEach var="paper" items="${paperList}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${paper.title}</td>
                <td>${paper.pername}</td>
                <td>${paper.creationDate}</td>
                <td>${paper.modifyDate}</td>
                <td>
                    <a href="#">修改</a>
                    &nbsp; | &nbsp;
                    <a href="#">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

<script>

</script>

</body>
</html>
