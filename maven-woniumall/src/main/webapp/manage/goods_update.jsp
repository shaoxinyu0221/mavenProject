<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>商品管理系统-商品信息修改</title>
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
			<!-- 路径导航  -->
			<ol class="breadcrumb">
				<li>你现在的位置是：<a href="">商品管理</a></li>
				<li class="active"><a href="">修改商品信息</a></li>
			</ol>
		</div>
		
		<div class="container">
			<form action="manage/goods" method="post" id="myform" enctype="multipart/form-data">
				<input type="hidden" name="opr" value="updateGoods">
				<input type="hidden" name="goodId" value="${good.id}">
				<table class="table table-bordered">
					<tr>
						<td class="col-md-1">商品名称：</td>
						<td class="text-center">
						<input type="text" class="form-control" name="goodsName" value="${good.name}"></td>
					</tr>

					<tr>
						<td>商品类型：</td>
						<td class="text-center">
							<select name="categoryId" class="form-control">
								<option value="">---请选择---</option>
								<c:forEach var="category" items="${categoryList}">
									<option value="${category.id}"
									<c:if test="${good.categoryId == category.id}">selected</c:if>
									>${category.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>商品图片：</td>
						<td class="text-center">
						    <input type="file" name="goodPicture" id="goodPicture" class="pull-left">
						</td>
					</tr>

					<tr>
						<td>库存：</td>
						<td class="text-center">
							<input type="number" class="form-control" name="goodStock" value="${good.stock}">
						</td>
					</tr>
					<tr>
						<td>作者：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="author" id="author">
						</td>
					</tr>
					<tr>
						<td>出版社：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="publisher" value="${good.publisher}">
						</td>
					</tr>
					<tr>
						<td>出版时间：</td>
						<td class="text-center">
							<input type="date" class="form-control" name="publishTime" value="${good.publishTime}">
						</td>
					</tr>
					<tr>
						<td>图书信息描述：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="description" value="${good.description}">
						</td>
					</tr>
					<tr>
						<td>市场价：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="marketPrice" value="${good.marketPrice}">
						</td>
					</tr>
					<tr>
						<td>销售价：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="salePrice" value="${good.salePrice}">
						</td>
					</tr>

					<tr>
						<td>是否最新</td>
						<td class="text-center">
							<select class="form-control" name="isNew" id="isNew">
								<c:if test="${good.isNew eq 'Y'}">
									<option value="Y" selected>是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${good.isNew eq 'N'}">
									<option value="Y">是</option>
									<option value="N" selected>否</option>
								</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td>是否热卖</td>
						<td class="text-center">
							<select class="form-control" name="isHot" id="isHot">
								<c:if test="${good.isHot eq 'Y'}">
									<option value="Y" selected>是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${good.isHot eq 'N'}">
									<option value="Y">是</option>
									<option value="N" selected>否</option>
								</c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button type="submit" class="btn btn-success">保存</button>
						</td>
					</tr>
				</table>
			</form>
		</div>

<script>
	if("${error}" != ""){
		layer.alert("${error}");
	}
</script>

</body>
</html>