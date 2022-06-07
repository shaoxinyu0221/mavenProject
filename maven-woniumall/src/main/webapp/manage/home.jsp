<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>图书管理-主页</title>
 	<link href="css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript">
    	
    </script>
</head>
<body>
	<div class="container-pull" style="padding-left: 10px; padding-right: 10px;">
		<h3 class="text-center">图书管理系统</h3>
		<h5>欢迎你：${sessionScope.adminCurrentLogin.account}</h5>
		<div class="pull-left text-center" style="width:20%;height:650px;border:1px blue solid;">
			<ul class="nav  nav-stacked">
				<li role="presentation" class="active">
				<a href="${pageContext.request.contextPath}/manage/category?opr=queryCategoryList" target="myframe"><span class="glyphicon glyphicon-th-large"></span>类别管理</a>
				</li>

				<li role="presentation">
				<a href="${pageContext.request.contextPath}/manage/goods?opr=gotoAddGoods" target="myframe"><span class="glyphicon glyphicon-plus"></span>添加商品</a>
				</li>

				<li role="presentation">
				<a href="${pageContext.request.contextPath}/manage/goods?opr=queryGoodsList" target="myframe"><span class="glyphicon glyphicon-book"></span>商品管理</a>
				</li>

				<li role="presentation">
				<a href="manage/user?opr=gotoUserList" target="myframe"><span class="glyphicon glyphicon-user"></span>用户管理</a>
				</li>

				<li role="presentation">
				<a href="manage/order?opr=gotoOrderList" target="myframe"><span class="glyphicon glyphicon-list-alt"></span>订单查询</a>
				</li>

				<li role="presentation">
				<a href="manage/admin?opr=logout"><span class="glyphicon glyphicon-log-out"></span>退出系统</a>
				</li>
			</ul>
		</div>
		<div class="pull-right" style="width:79%;height:650px;border:1px blue solid; margin-left: 10px">
			<iframe name="myframe" src="manage/welcome.jsp" width="100%" height="100%" frameborder="no"></iframe>
		</div>
	</div>
</body>
</html>