<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>订单管理系统-订单列表</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script src="js/layer/layer.js"></script>
    <script src="js/axios.min.js"></script>
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
		<div>
			<!-- 路径导航  -->
			<ol class="breadcrumb">
				<li class="active">你现在的位置是：订单管理</li>
				<li><a href="#">订单列表</a></li>
			</ol>
		</div>
		<!-- 路径导航结束 -->
		<div style="padding-left: 10px; padding-right: 10px;">
			<!-- 多条件查询 -->
				<form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/order" >
					<div class="form-group">
						<label for="userAccount">用户账号：</label>
						<input type="text" class="form-control" name="userAccount" id="userAccount" placeholder="请输入用户账号" value="${userAccount}">
					</div>
					<div class="form-group">
						<label >订单类型：</label>
						<select name="orderStatus" class="form-control" id="orderStatus">
							<option value="">---请选择---</option>
							<option value="0"<c:if test="${orderStatus eq '0'}">selected</c:if>>待付款</option>
							<option value="1"<c:if test="${orderStatus eq '1'}">selected</c:if>>待发货</option>
							<option value="2"<c:if test="${orderStatus eq '2'}">selected</c:if>>运输中</option>
							<option value="3"<c:if test="${orderStatus eq '3'}">selected</c:if>>待收货</option>
							<option value="4"<c:if test="${orderStatus eq '4'}">selected</c:if>>已完成</option>
						</select>
						<input type="hidden" name="opr" value="gotoOrderList">
						<button id="btnQuery" class="btn btn-primary">查询</button>
					</div>
				</form>
			</div>
			<!-- 多条件查询结束 -->
			<div style="padding-left: 10px; padding-right: 10px;">
			<!-- 数据列表 -->
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="col-md-1 text-center">
							<a href="goods_add.jsp"><span class="glyphicon glyphicon-plus"></span></a>
							序号
						</th>
						<th class="col-md-1 text-center"><input type="checkbox" name="chkAll" id="checkAll" onclick="selectAll()"/></th>
						<th class="col-md-1 text-center">订单编号</th>
						<th class="col-md-1 text-center">下单时间</th>
						<th class="col-md-1 text-center">用户账号</th>
						<th class="col-md-1 text-center">收货人</th>
						<th class="col-md-1 text-center">手机号码</th>
						<th class="col-md-1 text-center">收货地址</th>
						<th class="col-md-1 text-center">金额</th>
						<th class="col-md-1 text-center">订单状态</th>
						<th class="col-md-2 text-center">操作</th>
					</tr>
				</thead>
				<tbody id="tbd">
					<c:forEach var="order" items="${orderInfo.list}" varStatus="vs">
						<tr class="text-center">
							<td>${vs.count + requestScope.orderInfo.startRow-1}</td>
							<td><input type="checkbox" name="chk" id="chk" onclick="selectChk()"/></td>
							<td>${order.no}</td>
							<td>${order.orderTime}</td>
							<td>${order.user.account}</td>
							<td>${order.accept}</td>
							<td>${order.telephone}</td>
							<td>${order.address}</td>
							<td>${order.totalMoney}</td>
							<td>
								<c:if test="${order.status eq '0'}">待付款</c:if>
								<c:if test="${order.status eq '1'}">待发货</c:if>
								<c:if test="${order.status eq '2'}">运输中</c:if>
								<c:if test="${order.status eq '3'}">待收货</c:if>
								<c:if test="${order.status eq '4'}">已完成</c:if>
							</td>
							<td>
								<a href="javaScript:viewOrder(${order.id})" id="viewOrderItem"><span class="glyphicon glyphicon-pencil"></span>查看</a>&nbsp;&nbsp;&nbsp;
								<a href="manage/order?opr=changeStatus&orderId=${order.id}">
									<c:if test="${order.status eq '1'}"><span class="glyphicon glyphicon-pencil">发货</span></c:if>
								</a>
							</td>
						</tr>
					</c:forEach>

				</tbody>
				<tfoot>
					<tr>
						<td colspan="13">
							<!--分页组件-->
							<div style="margin:0px;text-align:center;">
								<ul class="pagination">
									<c:if test="${requestScope.orderInfo.pageNum eq 1}">
										<li><a  title="首页">&laquo;</a></li>
										<li><a  title="上一页">&lsaquo;</a></li>
									</c:if>
									<c:if test="${requestScope.orderInfo.pageNum ne 1}">
										<li><a href="javascript:changePage(1)" title="首页">&laquo;</a></li>
										<li><a href="javascript:changePage(${requestScope.orderInfo.prePage})" title="上一页">&lsaquo;</a></li>
									</c:if>

									<c:forEach var="pageNum" items="${requestScope.orderInfo.navigatepageNums}">
										<li class="<c:if test="${pageNum eq requestScope.orderInfo.pageNum}">active</c:if>">
											<a href="javascript:changePage(${pageNum})">${pageNum}</a>
										</li>
									</c:forEach>

									<c:if test="${requestScope.orderInfo.pageNum eq requestScope.orderInfo.pages}">
										<li><a  title="下一页">&rsaquo;</a></li>
										<li><a  title="末页">&raquo;</a></li>
									</c:if>
									<c:if test="${requestScope.orderInfo.pageNum ne requestScope.orderInfo.pages}">
										<li><a href="javaScript:changePage(${requestScope.orderInfo.nextPage})" title="下一页">&rsaquo;</a></li>
										<li><a href="javaScript:changePage(${requestScope.orderInfo.pages})" title="末页">&raquo;</a></li>
									</c:if>
									<li>
										&nbsp;&nbsp;总记录 ${requestScope.orderInfo.total} 条
										&nbsp;&nbsp;总页数 ${requestScope.orderInfo.pages} 页
										&nbsp;&nbsp;转到
										<input type="text" style="width:30px;text-align: center;" class="input" value="1" id="number" /> 页
										<select name="pageSize" id="pageSize">
											<option value="5">每页展示5条数据</option>
											<option value="10">每页展示10条数据</option>
											<option value="15">每页展示15条数据</option>
											<option value="20">每页展示20条数据</option>
										</select>
										<button type="button" class="btn btn-primary active" onclick="changePageNum()">确定</button>
									</li>
								</ul>
							</div>
							</nav>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
	</div>
	<div>
		<table class="table table-bordered">
			<caption>订单详情</caption>
			<thead>
			<tr>
				<th>序号</th>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>数量</th>
				<th>价格</th>
				<th>小计</th>
			</tr>
			</thead>
			<tbody id="tbody">

			</tbody>
		</table>
	</div>


	<script>
		function viewOrder(orderId){
			axios.get('manage/order?opr=gotoOrderItem&orderId='+orderId).then(function(result){
				let data = result.data;
				let tbody = document.getElementById('tbody');
				//清空tbody
				tbody.innerHTML = '';
				let i = 1;
				for (let item of data){
					//创建tr
					let tr = document.createElement('tr');
					//创建td
					let td1 = document.createElement('td');
					let td2 = document.createElement('td');
					let td3 = document.createElement('td');
					let td4 = document.createElement('td');
					let td5 = document.createElement('td');
					let td6 = document.createElement('td');
					//设置td内容
					td1.innerText = i;
					i++;
					td2.innerText = item.goods.name;
					td3.innerHTML = '<img src="' + item.goods.img + '"/>';
					td4.innerText = item.num;
					td5.innerText = item.price;
					td6.innerText = item.price * item.num;
					//添加td到tr
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					tr.appendChild(td6);
					//添加tr到tbody
					tbody.appendChild(tr);
				}
			}).catch(function(e){
				alert("服务器繁忙,查看订单失败")
			})
		}


		if ("${success}" != ""){
			layer.alert("${success}");
		}

		function changePageNum() {
			let pageNumber = document.getElementById("number").value;
			let pageSize = document.getElementById("pageSize").value;
			window.location.href = "${pageContext.request.contextPath}/manage/order?opr=gotoOrderList&pageNum="+pageNumber+"&pageSize="+pageSize;
		}

		function changePage(pageNumber){
			// let goodName = document.getElementById("goodName").value;
			// let goodTypeId = document.getElementById("goodTypeId").value;
			let pageSize = "${pageSize}"
			window.location.href = "${pageContext.request.contextPath}/manage/order?opr=gotoOrderList&pageNum="+pageNumber+"&pageSize="+pageSize;
		}

		function selectAll() {
			let chks = document.getElementsByName("chk")
			let chkAll = document.getElementById("checkAll")
			for (let chk of chks) {
				chk.checked = chkAll.checked;
			}
		}

		function selectChk() {
			let chks = document.getElementsByName("chk")
			let chkAll = document.getElementById("checkAll")
			for (let chk of chks) {
				if (chk.checked == false){
					chkAll.checked = false;
					return;
				}
			}
			chkAll.checked = true;
		}

	</script>
</body>
</html>