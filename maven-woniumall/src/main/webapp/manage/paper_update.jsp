<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<base href="${pageContext.request.contextPath}/">
<title>论文管理系统-论文列表</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
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
				<li>你现在的位置是：<a href="">论文管理</a></li>
				<li class="active">修改论文</a></li>
			</ol>
		</div>
		
		<div class="container">
			<form action="" method="post" id="myform">
				<input type="hidden" name="id">
				<table class="table table-bordered">
					<tr>
						<td class="col-md-1">论文标题：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="title" id="title"
							placeholder="请输入论文标题"></td>
					</tr>
					<tr>
						<td>论文摘要：</td>
						<td class="text-center">
							<textarea rows="5" cols="160" name="paper"></textarea>
						</td>
					</tr>
					<tr>
						<td>论文类型：</td>
						<td class="text-center">
							<select name="typeid" class="form-control">
								<option value="-1">---请选择---</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>论文：</td>
						<td class="text-center">
						    <input type="file" name="paperFile" id="paperFile" class="pull-left"> 
						</td>
					</tr>
					<tr>
						<td>论文文件名：</td>
						<td class="text-center">
							<input type="text" class="form-control" name="fineName" id="fineName"
							placeholder="请输入论文文件名"></td>
					</tr>
					<tr>
						<td>论文状态：</td>
						<td>
							<input type="radio"  name="status" id="status" value="y">正常
							<input type="radio"  name="status" id="status" value="n">作废
					   </td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button type="submit" class="btn btn-success">修改</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>