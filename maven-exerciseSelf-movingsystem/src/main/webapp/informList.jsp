<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/1
  Time: 下午 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预约列表</title>
</head>
<body>
    <h1>预约列表</h1>
    <table>
        <tr>
            <th>起始地区</th>
            <th>所用车型</th>
            <th>搬家日期</th>
            <th>联系人</th>
            <th>联系电话</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach var="inform" items="${informList}" >
            <tr>
                <th>${inform.area}</th>
                <th>${inform.car}</th>
                <th>${inform.movingdate}</th>
                <th>${inform.contact}</th>
                <th>${inform.telephone}</th>
                <th>${inform.status}</th>
                <th>
                    <a>详情</a>
                    <a>详情</a>
                </th>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
