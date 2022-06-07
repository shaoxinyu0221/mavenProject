<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>论文管理系统-论文列表</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
<style type="text/css">
.table tbody tr td {
	vertical-align: middle;
}
table {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div>
		<div>
			<!-- 路径导航  -->
			<ol class="breadcrumb">
				<li class="active">你现在的位置是：类别管理</li>
				<li><a href="#">类别列表</a></li>
			</ol>
		</div>
		<!-- 路径导航结束 -->

			<!-- 多条件查询结束 -->
			<div style="padding-left: 10px; padding-right: 10px;">
			<!-- 数据列表 -->
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="col-md-1 text-center">
							<a href="#添加类别"><span class="glyphicon glyphicon-plus"></span></a>
							序号
						</th>
						<th class="col-md-3 text-center">类别名称</th>
						<th class="col-md-3 text-center">类别状态</th>
						<th class="col-md-3 text-center">操作</th>
					</tr>
				</thead>
				<tbody id="tbd">
					<c:forEach var="category" items="${categoryList}" varStatus="vs">
						<tr class="text-center">
							<td>${vs.count}</td>
							<td>${category.name}</td>
							<td>${category.status eq 1?"正常":"禁用"}</td>
							<td>
								<a href="${pageContext.request.contextPath}/manage/category?opr=gotoCategoryUpdate&categoryId=${category.id}"><span class="glyphicon glyphicon-pencil"></span>修改</a>&nbsp;&nbsp;
								<a onclick="return confirm('是否确认删除')" href="${pageContext.request.contextPath}/manage/category?opr=categoryDelete&categoryId=${category.id}"><span class="glyphicon glyphicon-trash"></span>删除</a>&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<button type="button" class="btn btn-success" onclick="gotoCategoryAdd()">添加类别</button>

	<script>

		if ("${success}" != ""){
			layer.alert("${success}");
		}

		if ("${error}" != ""){
			layer.alert("${error}");
		}

		function gotoCategoryAdd(){
			window.location.href = "${pageContext.request.contextPath}/manage/category?opr=gotoCategoryAdd";
		}
	</script>
</body>
</html>