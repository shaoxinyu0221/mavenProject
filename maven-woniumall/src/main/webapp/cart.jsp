<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>购物车_${site}</title>
	<script src="js/jquery-3.5.1.js"></script>
<jsp:include page="base.jsp" />
<script type="text/javascript">
	function finish() {
		$("#form1").submit();
	}
</script>
	<script src="js/axios.min.js"></script>
<script type="text/plain" id="goods">
	<tr>
<td><input type="checkbox"></td>
						<td><input type="hidden" name="orderDetails[{{index}}].goods.id" value="{{id}}"/>
						<img src="{{thumbnail}}" width="66px"
							height="66px" alt="{{name}}"
							title="{{name}}" /></td>
						<td class="t_l"><a href="" class="blue">{{name}}</a></td>
						<td>￥<b>{{price2}}</b></td>
						<td>
							<div class="num">
								<a class="reduce" href="javascript:void(0)" onclick=''>-</a> <input
									name="orderDetails[{{index}}].nums" class="tiny" value="{{goodsNum}}" onblur='' type="text" id="goods_count_3">
								<a class="add" href="javascript:void(0)" onclick=''>+</a>
							</div>
						</td>
						<td>￥<b class="red2" id="goods_sum_3">{{goodsNum*price2}}</b></td>
						<td><a href="">删除</a></td>
					</tr>
</script>
</head>
<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<div class="position mt_10">
				<span>您当前的位置：</span> <a href=""> 首页</a> » 购物车
			</div>
			<div class="myshopping m_10">
				<ul class="order_step">
					<li class="current"><span class="first">1、查看购物车</span></li>
					<li><span>2、填写核对订单信息</span></li>
					<li class="last"><span>3、成功提交订单</span></li>
				</ul>
			</div>
			<form id="form1">
				<table width="100%" class="cart_table m_10">
					<col width="115px" />
					<col width="400px" />
					<col width="80px" />
					<col width="80px" />
					<col width="80px" />
					<caption>查看购物车</caption>
					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll" onclick="selectAll()">选择</th>
							<th>图片</th>
							<th>商品名称</th>
							<th>单价</th>
							<th>数量</th>
							<th>小计</th>
							<th class="last">操作</th>
						</tr>
					</thead>
					<tbody id="goodsList">
					<c:forEach var="cart" items="${cartList}">
						<tr>
							<td><input type="checkbox" name="goodsId" onclick="selectChk()" value="${cart.goods.id}"
								<c:if test="${cart.isChecked eq '1'}">checked</c:if>
							></td>
							<td><input type="hidden" name="deleteGoodsId" value="${cart.goods.id}"/>
								<img src="${cart.goods.img}" width="66px"
									 height="66px" alt=""
									 title="" /></td>
							<td class="t_l"><a href="goods/goodsView?opr=gotoGoodsView&goodsId=${cart.goods.id}" class="blue">${cart.goods.name}</a></td>
							<td>￥<b class="goodsPrice">${cart.goods.salePrice}</b></td>
							<td>
								<div class="num">
									<a class="reduce" href="javascript:reduceGoodsNum('${cart.goods.id}')" id="reduceGoodsNum${cart.goods.id}">-</a> <input
										id="cartGoodsNum${cart.goods.id}" name="cartGoodsNum" class="tiny" value="${cart.num}" type="text" onblur="changeGoodsNum('${cart.goods.id}')">
									<a class="add" href="javascript:addGoodsNum('${cart.goods.id}')" id="addGoodsNum${cart.goods.id}">+</a>
								</div>
							</td>
							<td>￥<b class="red2" name="goods_sum_3">${cart.num * cart.goods.salePrice}</b></td>
							<td><a href="javascript:deleteCart(${cart.goods.id})" id="deleteCart">删除</a></td>
						</tr>
					</c:forEach>
						<tr class="stats">
							<td colspan="8">金额总计（不含运费）：￥<b class="orange" id='sum_price'></b></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" class="t_l"></td>
							<td colspan="6" class="t_r"><a class="btn_continue" href="">继续购物</a>
							<a class="btn_pay" href="javascript:gotoOrder();form1.action='shopping?opr=gotoOrderAdd';form1.method='post';form1.submit();">去结算</a></td>
						</tr>
					</tfoot>
				</table>
			</form>
		<jsp:include page="footer.jsp" />
	</div>



	<script>
		window.addEventListener("load",function() {
			let chks = document.getElementsByName("goodsId")
			let chkAll = document.getElementById("checkAll")
			for (let chk of chks) {
				if (chk.checked == false){
						chkAll.checked = false;
						return;
				}
			}
			chkAll.checked = true;
			getSalePrice();
		})


		//减少商品数量的方法
		function reduceGoodsNum(goodsId){
			let goodNum = document.getElementById("cartGoodsNum"+goodsId).value;
			if (goodNum<=1){
				document.getElementById("cartGoodsNum"+goodsId).value = 1;
				return;
			}
			axios.post("shopping?opr=reduceGoodsNum&goodsId="+goodsId).then(function(result){
				if(result.data.code == 200){
					document.getElementById("cartGoodsNum"+goodsId).value = Number(goodNum)-1;
				}else {
					alert("服务器繁忙")
				}
			})
			let num = document.getElementById("cartGoodsNum"+goodsId).value;
			changeSingleGoodAllPrice("cartGoodsNum"+goodsId,Number(num)-Number(1));
			getSalePrice();
		}

		//增加商品数量的方法
		function addGoodsNum(goodsId){
			let goodNum = document.getElementById("cartGoodsNum"+goodsId).value
			if (goodNum<=1){
				document.getElementById("cartGoodsNum"+goodsId).value = 1;
				return;
			}
			axios.post("shopping?opr=addGoodsNum&goodsId="+goodsId).then(function(result){
				if(result.data.code == 200){
					document.getElementById("cartGoodsNum"+goodsId).value = Number(goodNum)+1;
				}else {
					alert("服务器繁忙")
				}
			})
			let num = document.getElementById("cartGoodsNum"+goodsId).value;
			changeSingleGoodAllPrice("cartGoodsNum"+goodsId,Number(num)+Number(1));
			getSalePrice();
		}

		//通过文本框直接修改商品数量
		function changeGoodsNum(goodsId){
			let goodNum = document.getElementById("cartGoodsNum"+goodsId).value
			if (goodNum<=1){
				document.getElementById("cartGoodsNum"+goodsId).value = 1;
				return;
			}
			axios.post("shopping?opr=changeNumFromCart&goodNum="+goodNum+"&goodsId="+goodsId).then(function(result){
				if(result.data.code == 200){
					document.getElementById("cartGoodsNum"+goodsId).value = goodNum;
				}else {
					alert("服务器繁忙")
				}
			})
			let num = document.getElementById("cartGoodsNum"+goodsId).value;
			changeSingleGoodAllPrice("cartGoodsNum"+goodsId,num);
			getSalePrice();
		}

		//删除商品
		function deleteCart(deleteGoodsId){
			alert(document.getElementById("deleteCart").parentElement.parentElement)
			axios.post("shopping?opr=deleteCart&deleteGoodsId="+deleteGoodsId).then(function(result){
				let res = result.data;
				if (res.code != 200){
					alert("删除错误")
				}else{
					alert(res.msg)
					document.getElementById("deleteCart").parentElement.parentElement.remove();
				}
			})
			getSalePrice();
		}

		//提交表单时,将num属性附加到选中的复选框后
		function gotoOrder(){
			let chks = document.getElementsByName("goodsId")
			// let buyNumbers = new Array();
			// let goodsId = new Array();
			let j = 0;
			for (let i=0;i<chks.length;i++) {
				let chk = chks[i];
				if(chk.checked == true){
					//获取到他的父元素,tr
					let row = chk.parentNode.parentNode;
					//找到父元素的子元素,num的值
					let num = row.children[4].children[0].children[1].value;
					//向tr中添加一行td,再向td中添加一行input,属性是hidden,value是num
					var td = document.createElement("td")
					td.innerHTML= "<input type='hidden' name='buyNums' value=" + num + ">"
					row.appendChild(td)
				}
			}
		}


		//全选按钮
		function selectAll() {
			let chks = document.getElementsByName("goodsId")
			let chkAll = document.getElementById("checkAll")
			//如果全选被选中,就将订单中的所有商品选中
			if(chkAll.checked == true){
				for (let chk of chks) {
					axios.post("cart?opr=changeCartSelected&goodsId="+chk.value).then(function(result){
						if (result.data.code == 200){
							chk.checked = chkAll.checked;
							getSalePrice();
						}
					})
				}
			}else {
				for (let chk of chks) {
					axios.post("cart?opr=changeCartNotSelected&goodsId="+chk.value).then(function(result){
						if (result.data.code == 200){
							chk.checked = chkAll.checked;
							getSalePrice();
						}
					})
				}
			}

		}

		//子复选框按钮
		function selectChk() {
			let chks = document.getElementsByName("goodsId")
			let chkAll = document.getElementById("checkAll")
			for (let chk of chks) {
				if (chk.checked == false){
					axios.post("cart?opr=changeCartNotSelected&goodsId="+chk.value).then(function(result){
						if (result.data.code == 200){
							chkAll.checked = false;
							getSalePrice();
							return;
						}
					})
				}else {
					axios.post("cart?opr=changeCartSelected&goodsId="+chk.value).then(function(result){
						if (result.data.code == 200){

						}
					})
				}
			}
			getSalePrice();
			chkAll.checked = true;
		}


		//获取总价的方法
		function getSalePrice(){
			//设置值存储商品总价
			let allPrice = 0;
			//拿到所有复选框
			let chks = document.getElementsByName("goodsId")
			for (let chk of chks){
				//如果被选中,拿到这行的小计
				if (chk.checked == true){
					//获取到他的父元素,tr
					let row = chk.parentNode.parentNode;
					//找到父元素的子元素,num的值
					let price = row.children[5].children[0].innerHTML;
					allPrice = allPrice + Number(price);
				}
			}
			//设置值
			document.getElementById("sum_price").innerText = allPrice.toFixed(2);
		}


		//修改小计的方法,第一个参数是元素的id,第二个参数是商品商品数量
		function changeSingleGoodAllPrice(elementId,num){
			//拿到商品大家
			let singlePrice = document.getElementById(elementId).parentElement.parentElement.parentElement.children[3].children[0].innerText;
			//设置小计的值
			let singleGoodAllPrice = Number(num)*Number(singlePrice)
			document.getElementById(elementId).parentElement.parentElement.parentElement.children[5].children[0].innerHTML = singleGoodAllPrice.toFixed(2);
		}
	</script>
</body>
</html>
