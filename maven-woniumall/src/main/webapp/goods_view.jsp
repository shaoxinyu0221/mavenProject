<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<base href="${base}/" />
<meta charset="utf-8" />
<title>${goods.name}_${site}</title>
<jsp:include page="base.jsp" />
<script src="js/axios.min.js"></script>
</head>
<body class="index">
	<div id="content"></div>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="navbar.jsp"></jsp:include>
		<jsp:include page="search.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<div class="summary">
				<h2>${goods.name}</h2>
				<!--基本信息区域-->
				<ul>
					<li><span class="f_r light_gray">商品编号：<label
							id="data_goodsNo">${goods.no}</label></span></li>
					<li id="priceLi">销售价：<b class="price red2"><span
							class="f30" id="real_price">￥${goods.salePrice}</span></b></li>
					<li>市场价：<s id="data_marketPrice">￥${goods.marketPrice}</s>
					</li>
					<li>库存：现货<span>(<label id="data_storeNums">${goods.stock}</label>)
					</span></li>
					<li>作者:<span>${goods.author}</span></li>
					<li>出版社:<span>${goods.publisher}</span></li>
					<li>出版时间:<span>${goods.publishTime}</span></li>
					<li>顾客评分：<span class="grade"><i style="width:0px;"></i></span>(已有0人评价)
					</li>
				</ul>
				<div class="current">
					<dl class="m_10 clearfix">
						<dt>购买数量：</dt>
						<dd>
							<input class="gray_t f_l" type="text" id="buyNums" value="1"
								maxlength="5" name="buyNums"/>
							<div class="resize">
								<a class="add" id="add"></a> <a class="reduce" id="reduce"></a>
							</div>
						</dd>
					</dl>
					<input class="submit_buy" type="button" id="buyNowButton"
						value="立即购买" onclick="order_add()" />
						<div class="shop_cart" style="z-index:1">
						<input class="submit_join" type="button" id="joinCarButton"
							onclick="joinCart();" value="加入购物车" />

						<div class="shopping" id="product_myCart" style='display:none'>
							<dl class="cart_stats">
								<dt class="gray f14 bold">
									<a class="close_2 f_r" href="javascript:closeCartDiv();"
										title="关闭">关闭</a> <img src="images/front/right_s.gif"
										width="24" height="24" alt="" />成功加入购物车
								</dt>
								<dd class="gray">
									目前选购商品共<b class="orange" name='mycart_count' id="mycartCount"></b>件<span>合计：<b
										name='mycart_sum' id="mycart_sum"></b></span>
								</dd>
								<dd>
									<a class="btn_blue bold" href="shopping?opr=gotoCart">进入购物车</a><a
										class="btn_blue bold" href="javascript:void(0)"
										onclick="closeCartDiv();">继续购物>></a>
								</dd>
							</dl>
						</div>
					</div>
				</div>

			</div>

			<!--图片放大镜-->
			<div>
				<div class="pic_show"
					style="width:435px;height:435px;position:relative;z-index:5;padding-bottom:5px;">
					<img src="${goods.img}" title="${goods.name}"
						style="border:none;width:435px;height:435px" />
				</div>
			</div>
		</div>

		<div class="wrapper clearfix container_2">
			<!--左边栏-->
			<div class="sidebar f_l">
				<div class="box m_10">
					<div class="title">热卖商品</div>
					<div class="content">
						<ul class="ranklist">
							<c:forEach var="hotGoods" items="${goodsInfoIsHot.list}">
								<li class="current">
									<a href=""><img width="58px" height="58px" alt="" src="${hotGoods.img}"/></a>
									<a title="" class="p_name" href=""></a>
									<b>￥${hotGoods.salePrice}</b>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

			<!--滑动面tab标签-->
			<div class="main f_r" style="overflow:hidden">
				<div class="uc_title" name="showButton">
					<label class="current"><span>商品详情</span></label> <label><span>顾客评价(0)</span></label>
					<label><span>购买记录(1)</span></label>
				</div>
				<div name="showBox">
					<!-- 商品详情 start -->
					<div>${goods.description}</div>
					<!-- 商品详情 end -->

					<!-- 顾客评论 start -->
					<div class="hidden comment_list box">
						<div class="title3">
							<span class="f_r f12 light_gray normal"> 只有购买过该商品的用户才能进行评价
							</span> <img src="images/front/comm.gif" width="16px" height="16px" />商品评论<span
								class="f12 normal">（已有<b class="red2">0</b>条）
							</span>
						</div>
						<c:if test="${not empty sessionScope.user}">
							<div>
								<textarea id="judgecontent"></textarea>
								<input type="button" value="发表" id="judge" />
							</div>
						</c:if>
						<div id='commentBox'></div>
					</div>
					<!-- 顾客评论 end -->

					<!-- 购买记录 start -->
					<div class="hidden box">
						<div class="title3">
							<img src="images/front/cart.gif" width="16" height="16" alt="" />
							购买记录<span class="f12 normal">（已有<b class="red2">1</b>购买）
							</span>
						</div>

						<table width="100%" class="list_table m_10 mt_10">
							<col width="150" />
							<col width="120" />
							<col width="120" />
							<col width="150" />
							<col />
							<thead class="thead">
								<tr>
									<th>购买人</th>
									<th>出价</th>
									<th>数量</th>
									<th>购买时间</th>
									<th>状态</th>
								</tr>
							</thead>
						</table>
						<table width="100%" class="list_table m_10">
							<col width="150" />
							<col width="120" />
							<col width="120" />
							<col width="150" />
							<col />
							<tbody class="dashed" id="historyBox"></tbody>
						</table>
					</div>
					<!-- 购买记录 end -->
				</div>
			</div>
		</div>
		<jsp:include page="help.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

<script>
	document.getElementById("add").addEventListener("click", function() {
		var num = document.getElementById("buyNums").value;
		num++;
		document.getElementById("buyNums").value = num;
	});

	document.getElementById("reduce").addEventListener("click", function() {
		var num = document.getElementById("buyNums").value;
		num--;
		if (num==0) {
			num = 1;
		}
		document.getElementById("buyNums").value = num;
	});

	//点击立即购买
	function order_add() {
		//跳转到order_add.jsp
		let goodsId = "${goods.id}";
		let buyNums = document.getElementById("buyNums").value;
		window.location.href = "shopping?opr=gotoOrderAdd&goodsId="+goodsId+"&buyNums="+buyNums;
	}

	//点击加入购物车
	function joinCart(){
		let buyNums = document.getElementById("buyNums").value;
		if ("${userCurrentLogin}" == ""){
			alert("当前未登录,请先登录");
			return;
		}
		let data = {
			"opr": "joinCart",
			"goodsId": "${goods.id}",
			"buyNums": buyNums
		}
		axios.get("shopping", {params:data}).then(function(result){
			if(result.data.code != 200){
				alert("加入购物车失败");
			}else {
				//查询当前用户购物车信息
				axios.get("shopping?opr=viewCartList").then(function(result){
					if(result.data.code == 200){
						let res = result.data.data.split(",");
						document.getElementById("mycartCount").innerHTML = res[0];
						document.getElementById("mycart_sum").innerText = res[1];
						document.getElementById("product_myCart").style.display="block";
					}else{
						alert("服务器繁忙,请稍后重试!")
					}
				})
			}
		}).catch(function(e){
			console.log(e);
		});
	}

	function closeCartDiv(){
		document.getElementById("product_myCart").style.display="none";
	}

</script>
</body>
</html>