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
            <span>论文标题:</span><input type="text" name="title" id="title" placeholder="请输入论文名称" value="${title}"/>
            <span>论文类型:</span>
            <select name="typeId" id="typeId">
                <option value="">---请选择---</option>
                <c:forEach var="types" items="${paperTypes}">
                    <option value="${types.id}"
                            <c:if test="${types.id eq typeId}">selected</c:if>
                    >${types.pername}</option>
                </c:forEach>
            </select>
            <input type="submit" value="查询" />
            <input type="button" value="清空查询条件" onclick="clearCondition()"/>
            <button type="button" onclick="window.open('paper?opr=paperAdd')">添加</button>
            <button type="button" onclick="return confirm('是否确认删除'),deleteBatch()">批量删除</button>
        </form>

    </div>
    <table class="paperList" align="center" border="1">
        <tr>
            <td>序号</td>
            <td><input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll()"></td>
            <td>论文标题</td>
            <td>论文类别</td>
            <td>发表时间</td>
            <td>修改时间</td>
            <td>查看论文</td>
            <td>操作</td>
        </tr>
        <c:forEach var="paper" items="${paperList}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td><input type="checkbox" name="chk" value="${paper.id}" onclick="chkChange(this)"></td>
                <td>${paper.title}</td>
                <td>${paper.pername}</td>
                <td>${paper.creationDate}</td>
                <td>${paper.modifyDate}</td>
                <td><a href="${pageContext.request.contextPath}/paper?opr=download&downId=${paper.id}">点击下载</a></td>
                <td>
                    <a href="${pageContext.request.contextPath}/paper?opr=paperUpdate&id=${paper.id}">修改</a>
                    &nbsp; | &nbsp;
                    <a href="${pageContext.request.contextPath}/paper?opr=paperDelete&deleteId=${paper.id}" onclick="confirm('是否确认你删除')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p style="color:red" align="center">${success}</p>
    <p style="color:red" align="center">${modifySuccess}</p>
    <p style="color:red" align="center">${deleteSuccess}</p>
    <p style="color:red" align="center">${deleteError}</p>
<script>
    function clearCondition(){
        document.getElementById("typeId").value=""
        document.getElementById("title").value=""
    }

    function deleteBatch(){
        let chks = document.getElementsByName("chk");
        let ids = "";
        for (let chk of chks) {
            if(chk.checked){
                ids = ids + chk.value + ",";
            }
        }
        //去掉最后的逗号
        ids = ids.substring(0,ids.length-1);
        //把ids传到servlet
        window.location.href = "${pageContext.request.contextPath}/paper?opr=deleteBatch&ids="+ids;
    }

    //全选功能
    function checkAll(){
        let checkall = document.getElementById("checkAll");
        let chks = document.getElementsByName("chk");
        for (let chk of chks){
            if(checkall.checked){
                chk.checked = true;
            }else {
                chk.checked = false;
            }
        }
    }

    //反选功能
    function chkChange(){
        let chks = document.getElementsByName("chk");
        let checkall = document.getElementById("checkAll");
        for (let chk of chks){
            if(chk.checked == false){
                checkall.checked = false;
                return;
            }
        }
        checkall.checked = true;
    }

</script>

</body>
</html>
