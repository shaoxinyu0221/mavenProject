<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<base href="${base}/" />
<title>核对订单信息-${site}</title>
<jsp:include page="base.jsp" />
</head>
<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp" />
		<div class="wrapper clearfix">
			<div class="position mt_10">
				<span>您当前的位置：</span> <a href=""> 首页</a> » 填写核对订单信息
			</div>
			<div class="myshopping m_10">
				<ul class="order_step">
					<li class="current_prev"><span class="first"><a
							href='javascript:window.history.go(-1);'>1、查看购物车</a></span></li>
					<li class="current"><span>2、填写核对订单信息</span></li>
					<li class="last"><span>3、成功提交订单</span></li>
				</ul>
			</div>

			<form action='shopping?opr=gotoSubmit' method='post' name='order_form'>
				<div class="cart_box m_10">
					<div class="title">填写核对订单信息</div>
					<div class="cont">

						<!--地址管理 开始-->
						<div class="wrap_box">
							<h3>
								<span class="orange">收货人信息</span>
							</h3>
							<!--收货表单信息 开始-->
							<div class="prompt_4 m_10" id='address_often'>
								<strong>常用收货地址</strong>
								<ul class="addr_list">
									<c:forEach items="${address}" var="address">
										<li>
											<label>
												<input class="radio" name="orderAddress" type="radio" value="${address.accept}-${address.telephone}-${address.province}${address.city}${address.area}${address.street}"
												<c:if test="${address.isDefault eq 'Y'}">checked</c:if> />
													${address.accept}&nbsp;&nbsp;&nbsp;&nbsp;${address.telephone}&nbsp;&nbsp;&nbsp;&nbsp;${address.province}
												${address.city} ${address.area} ${address.street}
											</label>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<!--地址管理 结束-->

						<!--支付方式 开始-->
						<div class="wrap_box" id='paymentBox'>
							<h3>
								<span class="orange">支付方式</span>
							</h3>

							<table width="100%" class="border_table" id='payment_form'>
								<col width="200px" />
								<col />

								<tr>
									<th><label><input class="radio" name="paytype"
											alt="0" title="支付宝" type="radio" value="0" />支付宝</label></th>
									<td>支付手续费：￥0</td>
								</tr>
								<tr>
									<th><label><input class="radio" name="paytype"
											alt="0" title="货到付款" type="radio" value="1" />货到付款</label></th>
									<td>支付手续费：￥0</td>
								</tr>
							</table>
						</div>
						<!--支付方式 结束-->
						<!--购买清单 开始-->
						<div class="wrap_box">
							<h3>
								<span class="orange">购买的商品</span>
							</h3>

							<table width="100%" class="cart_table t_c">
								<col width="115px" />
								<col />
								<col width="80px" />
								<col width="80px" />
								<col width="80px" />

								<thead>
									<tr>
										<th>图片</th>
										<th>商品名称</th>
										<th>单价</th>
										<th>数量</th>
										<th class="last">小计</th>
									</tr>
								</thead>

								<tbody>

									<c:set var="totalMoney" value="0"></c:set>
									<c:forEach items="${orderItemList}" var="orderDetail" varStatus="s">
										<input type="hidden" name="OrderGoodsId" value="${orderDetail.goods.id}">
										<input type="hidden" name="OrderGoodsNum" value="${orderDetail.num}">
										<tr>
											<td><img src="${orderDetail.goods.img}"
													 width="66px" height="66px" alt="${orderDetail.goods.name}"
													 title="${orderDetail.goods.name}" /></td>
											<td class="t_l"><a href="" class="blue">${orderDetail.goods.name}</a></td>
											<td>￥<b>${orderDetail.goods.salePrice}</b></td>
											<td>${orderDetail.num}</td>
											<td>￥<b class="red2">${orderDetail.goods.salePrice*orderDetail.num}</b></td>
											<c:set var="totalMoney" value="${totalMoney+orderDetail.goods.salePrice*orderDetail.num}"></c:set>
										</tr>
									</c:forEach>
									<!-- 商品展示 结束-->
								</tbody>
							</table>
						</div>
						<!--购买清单 结束-->

					</div>
				</div>

				<!--金额结算-->
				<div class="cart_box" id='amountBox'>
					<div class="cont_2">
						<strong>结算信息</strong>
						<div class="pink_box">
							<c:set var="deliveryfee" value="0"></c:set>
							<c:if test="${totalMoney lt 50}">
								<c:set var="deliveryfee" value="10.00"></c:set>
							</c:if>
							<p class="f14 t_l">
								商品总金额：<b>${totalMoney}</b> + 运费总计：<b id='delivery_fee_show'>${deliveryfee}</b>
							</p>
						</div>
						<hr class="dashed" />
						<div class="pink_box gray m_10">
							<table width="100%" class="form_table t_l">
								<col width="220px" />
								<col />
								<col width="250px" />
								<tr>
									<td class="t_r"><b class="price f14">应付总额：<span
											class="red2">￥<b id='final_sum'>${totalMoney+deliveryfee}</b></span>元
									</b></td>
								</tr>
							</table>
						</div>
						<p class="m_10 t_r">
						   <input type="hidden" name="totalMoney" value="${totalMoney+deliveryfee}">
							<input type="submit" class="submit_order" />
						</p>
					</div>
				</div>

			</form>

		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
