<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Index.jsp</title>
</head>
<body>
    <h1>顺风预约搬家等级</h1>
    <form action="book">
        <input type="hidden" name="opr" value="check"/>
        <table>
            <tr>
                <th>起始地区:</th>
                <td>
                    <select name="area">
                        <option>海淀区</option>
                        <option>朝阳区</option>
                        <option>西城区</option>
                        <option>东城区</option>
                        <option>丰台区</option>
                        <option>大兴区</option>
                        <option>石景山</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>所用车型:</th>
                <td>
                    <input name="car" type="radio" id="1"/><label for="1">金杯</label>
                    <input name="car" type="radio" id="2"/><label for="2">皮卡</label>
                    <input name="car" type="radio" id="3" checked/><label for="3">厢式货车</label>
                    <input name="car" type="radio" id="4"/><label for="4">1041货车</label>
                </td>
            </tr>
            <tr>
                <th>搬家日期:</th>
                <td><input type="date" name="movingdate"/></td>
            </tr>
            <tr>
                <th>联系人:</th>
                <td><input type="text" name="contact"></td>
            </tr>
            <tr>
                <th>联系电话:</th>
                <td><input type="text" name="telephone"/><span>-</span><input type="text" name="telephone"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="预约登记" />
                </td>
            </tr>
        </table>
    </form>
<p>${error}</p>
<p style="color:red">${checkSuccess}</p>
<p><a href="book?opr=gotoLogin">点击跳转管理员登录页面</a></p>
</body>
</html>
