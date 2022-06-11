<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<base href="${base}/" />
<meta charset="utf-8" />
<title>用户登录_蜗牛图书商城</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
	<script src="js/axios.min.js"></script>
</head>

<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="wrapper clearfix">
			<div class="wrap_box">
				<h3 class="notice">已注册用户，请登录</h3>
				<p class="tips">欢迎来到我们的网站，如果您已是本站会员请登录</p>
				<div class="box login_box clearfix">
					<form action='front/user' method="post">
					<input type="hidden" name="opr" value="login" />
						<table width="515" class="form_table f_l">
							<col width="120px" />
							<col />
							<tr>
								<th>用户名：</th>
								<td>
									<input class="gray" type="text" name="account"
									id="login_info" placeholder="请输入用户名" /><span id="namemsg" style="color:red"></span>
								</td>
							</tr>
							<tr>
								<th>密码：</th>
								<td><input class="gray" type="password" id="password"
									name="password" placeholder="请输入6-20位长度的密码" /><span
									id="pwdmsg"></span></td>
							</tr>
							<tr><th>验证码：</th><td><input type='text' class='gray_s' name='captcha' alt='填写下面图片所示的字符' id="captcha"/>
								<label id="checkCaptcha">填写下面图片所示的字符</label></td>
							</tr>
							<tr class="low"><th></th><td><img src='captcha' id='captchaImg' />
								<span class="light_gray">看不清？<a class="link" href="javascript:void(0)"
																onclick="changeCaptcha()">换一张</a></span>
							</td>
							</tr>
							<tr>
								<td></td>
								<td><button class="submit_login" id="submit_login" type="button">登录</button></td>
							</tr>
						</table>
					</form>

					<!--正常登录时-->
					<table width="360px" class="form_table prompt_3 f_l">
						<col width="75px" />
						<col />
						<tr>
							<th></th>
							<td>
								<p class="mt_10">
									<strong class="f14">您还不是<span class="orange">蜗牛图书商城</span>用户
									</strong>
								</p>
								<p>
									现在免费注册成为嗨购商城用户，便能立即享受便宜又放心的购物乐趣。<a class="blue" href="">网站首页>></a>
								</p>
								<p class="mt_10">
									<a class="reg_btn" href="register.jsp">注册新用户</a>
								</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>



<script>
	//更换验证码
	function changeCaptcha(){
		document.getElementById("captchaImg").src="captcha?randNum="+Math.random();
	}


	document.getElementById("submit_login").addEventListener("click",function(){
		let account = document.getElementById("login_info").value;
		let password = document.getElementById("password").value;
		let captcha = document.getElementById("captcha").value;
		axios.post("front/user?opr=login&account="+account+"&password="+password+"&captcha="+captcha).then(function(result){
			let msg = result.data;
			if (msg.code != 200) {
				alert(msg.msg)
			}else{
				window.location.href = "${base}/"
			}
		}).catch(function(e){
			console.log(e);
		})
	});
</script>
</body>
</html>
