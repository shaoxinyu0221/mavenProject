<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>提交订单-${site}</title>
<jsp:include page="base.jsp"></jsp:include>
</head>
<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp" />

		<div class="wrapper clearfix">
			<div class="position mt_10">
				<span>您当前的位置：</span> <a href=""> 首页</a> » 成功提交订单
			</div>
			<div class="myshopping m_10">
				<ul class="order_step">
					<li><span class="first">1、查看购物车</span></li>
					<li class="current_prev"><span>2、填写核对订单信息</span></li>
					<li class="last_current"><span>3、成功提交订单</span></li>
				</ul>
			</div>

			<div class="cart_box m_10">
				<div class="title">成功提交订单</div>
				<div class="cont">
					<p class="order_stats">
						<img width="48px" height="51px" alt=""
							src="images/front/right.gif"><strong class="f14">订单已提交</strong>
					</p>

					<div class="stats_box">
						<h3>订单信息</h3>
						<table width="100%" class="form_table t_l orange">
							<col width="75px" />
							<col />

							<tbody>
								<tr>
									<th>订单编号：</th>
									<td class="f18 bold red2">${order.no}</td>
								</tr>
								<tr>
									<th>订单金额：</th>
									<td class="f18 bold red2">￥<b>${order.totalMoney}</b></td>
								</tr>
								<tr>
									<th>支付方式：</th>
									<td class="f18 bold red2">${order.payType eq '0' ? "货到付款":"支付宝"}</td>
								</tr>

							</tbody>
						</table>

						<!--不是货到付款并且支付方式为线上支付-->
						<c:if test="${order.payType ne '1'}">
							<form action='order_pay' method='post' target='_blank'>
								<input type="hidden" name="orderId" value="${order.id}">
								<input type="hidden" name="payType" value="${order.payType}">
								<input class="submit_pay" type="submit" value="立即支付" />
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
