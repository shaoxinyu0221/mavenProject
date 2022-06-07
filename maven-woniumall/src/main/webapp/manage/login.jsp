<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="UTF-8">
	<title>论文管理-登录</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript">
    	
    </script>
</head>
<body>
	<div style="width:20%;margin:0 auto; text-align: center; padding-top:5% ">
		<p id="msg" style="color:red"></p>
		<form action="manage/admin" id="myform" method="post">
			<input type="hidden" name="opr" value="login" />
			<div class="form-group form-inline">
				<label for="account">用户名：</label>
		        <input type="text" class="form-control" id="account" name="account" placeholder="请输入用户名"
				value="${cookie.account.value}">
			</div>
			<div class="form-group form-inline">
				<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
		    	<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"
				value="${cookie.password.value}">
			</div>
			<div class="form-group form-inline">
				<label for="rememberMe">记住我：</label>
				<input type="checkbox" name="rememberMe" id="rememberMe" checked/>
			</div>
			<div class="form-group">
				<input class="btn btn-success btn-md" id="btnLogin" type="submit" value="登录">
				<button class="btn btn-danger btn-md"  type="reset">取消</button>
			</div>
		</form>
	</div>
	<p align="center" style="color:red">当前在线人数:${count}</p>
	<script>
		if ("${error}" != ""){
			layer.alert("${error}");
		}

		//判断是不是在顶层打开的，如果不是就重定向一下
		window.onload=function(){
			if(window.top != window.self){ //当前login.jsp页面不是在顶层打开
				top.location.href=location.href; //将当前窗口的地址设置到顶层窗口的地址栏
			}
		}
	</script>
</body>
</html>