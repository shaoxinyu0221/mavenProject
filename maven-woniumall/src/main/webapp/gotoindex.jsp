<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>首页_${site}</title>
<jsp:include page="base.jsp" />
</head>

<body class="index">
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="navbar.jsp"></jsp:include>
		<jsp:include page="search.jsp"></jsp:include>

		<div class="wrapper clearfix">
			<div class="sidebar f_r">
				<!--热卖商品-->
				<div class="hot box m_10">
					<div class="title">
						<h2>热卖商品</h2>
					</div>
					<div class="cont clearfix">
						<ul class="prolist">
							<c:forEach var="goods" items="${goodsInfoIsHot.list}">
								<li><a href="goods/goodsView?opr=gotoGoodsView&goodsId=${goods.id}"><img src="${goods.img}"
										width="85" height="85" alt="" /></a>
									<p class="pro_title">
										<a href="goods/goodsView?opr=gotoGoodsView&goodsId=${goods.id}">${goods.name}</a>
									</p>
									<p class="brown">
										<b>${goods.salePrice}</b>
									</p></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!--热卖商品-->

			</div>

			<div class="main f_l">
				<!--最新商品-->
				<div class="box yellow m_10">
					<div class="title title3">
						<h2>
							<img src="images/front/new_product.gif" alt="最新商品" width="160"
								height="36" />
						</h2>
					</div>
					<div class="cont clearfix">
						<ul class="prolist">
							<c:forEach var="newGood" items="${goodsInfoIsNew.list}">
								<li style="overflow: hidden"><a href="goods/goodsView?opr=gotoGoodsView&goodsId=${newGood.id}" target="_blank"><img
										src="${newGood.img}" width="175" height="175" alt="" /></a>
									<p class="pro_title">
										<a title="" href="goods/goodsView?opr=gotoGoodsView&goodsId=${newGood.id}">${newGood.name}</a>
									</p>
									<p class="brown">
										惊喜价：<b>￥${newGood.salePrice}</b>
									</p>
									<p class="light_gray">
										市场价：<s>￥${newGood.marketPrice}</s>
									</p></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!--最新商品-->
				<c:forEach items="${categories}" var="category">
					<div class="box m_10 green" name="showGoods">
						<div class="title title3">
							<h2>
								<a href=""><strong>${category.name}</strong></a>
							</h2>
							<a class="more" href="">更多商品...</a>
						</div>

						<div class="cont clearfix">
							<ul class="prolist">
								<c:forEach items="${category.goodses}" var="goods">
									<li style="overflow: hidden"><a href=""><img
											src="${goods.thumbnail}" width="175" height="175" alt=""
											title=""></a>
										<p class="pro_title">
											<a title="" href="">${goods.name}</a>
										</p>
										<p class="brown">
											惊喜价：<b>￥${goods.price2}</b>
										</p>
										<p class="light_gray">
											市场价：<s>￥${goods.price1}</s>
										</p></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<jsp:include page="help.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<script>
		if ("${error}"!= "" && "${error}"!= null){
			alert("${error}")
		}
	</script>
</body>
</html>