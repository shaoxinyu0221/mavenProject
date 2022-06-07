<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>商品管理系统-商品列表</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script src="js/layer/layer.js"></script>
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
				<li class="active">你现在的位置是：商品管理</li>
				<li><a href="#">商品列表</a></li>
			</ol>
		</div>
		<!-- 路径导航结束 -->
		<div style="padding-left: 10px; padding-right: 10px;">
			<!-- 多条件查询 -->
				<form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/goods" >
					<div class="form-group">
						<label for="goodName">商品名称：</label>
						<input type="text" class="form-control" name="goodName" id="goodName" placeholder="请输入商品名称" value="${goodName}">
					</div>
					<div class="form-group">
						<label >商品类型：</label>
						<select name="goodTypeId" class="form-control" id="goodTypeId">
							<option value="">---请选择---</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}"
										<c:if test="${goodTypeId == category.id}">selected</c:if>
								>${category.name}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="opr" value="queryGoodsList">
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
						<th class="col-md-1 text-center">商品名</th>
						<th class="col-md-1 text-center">类别</th>
						<th class="col-md-1 text-center">图片</th>
						<th class="col-md-1 text-center">库存</th>
						<th class="col-md-1 text-center">市场价</th>
						<th class="col-md-1 text-center">销售价</th>
						<th class="col-md-1 text-center">是否最新</th>
						<th class="col-md-1 text-center">是否热卖</th>
						<th class="col-md-1 text-center">状态</th>
						<th class="col-md-2 text-center">操作</th>
					</tr>
				</thead>
				<tbody id="tbd">
					<c:forEach var="goods" items="${goodsInfo.list}" varStatus="vs">
						<tr class="text-center">
							<td>${vs.count + requestScope.goodsInfo.startRow-1}</td>
							<td><input type="checkbox" name="chk" id="chk" onclick="selectChk()"/></td>
							<td>${goods.name}</td>
							<td>${goods.categoryName}</td>
							<td><img src="${goods.img}"/></td>
							<td>${goods.stock}</td>
							<td>${goods.marketPrice}</td>
							<td>${goods.salePrice}</td>
							<td>${goods.isNew eq 'Y'?'是':'否'}</td>
							<td>${goods.isHot eq 'Y'?'是':'否'}</td>
							<td>${goods.status eq 1?'正常':'下架'}</td>
							<td>
								<a href="manage/goods?opr=gotoGoodsUpdate&goodId=${goods.id}"><span class="glyphicon glyphicon-pencil"></span>修改</a>&nbsp;&nbsp;&nbsp;
								<a href="manage/goods?opr=changeGoodStatus&goodId=${goods.id}"><span class="glyphicon glyphicon-download-alt"></span>${goods.status eq 1?'下架':'上架'}</a>
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
									<c:if test="${requestScope.goodsInfo.pageNum eq 1}">
										<li><a  title="首页">&laquo;</a></li>
										<li><a  title="上一页">&lsaquo;</a></li>
									</c:if>
									<c:if test="${requestScope.goodsInfo.pageNum ne 1}">
										<li><a href="javascript:changePage(1)" title="首页">&laquo;</a></li>
										<li><a href="javascript:changePage(${requestScope.goodsInfo.prePage})" title="上一页">&lsaquo;</a></li>
									</c:if>

									<c:forEach var="pageNum" items="${requestScope.goodsInfo.navigatepageNums}">
										<li class="<c:if test="${pageNum eq requestScope.goodsInfo.pageNum}">active</c:if>">
											<a href="javascript:changePage(${pageNum})">${pageNum}</a>
										</li>
									</c:forEach>

									<c:if test="${requestScope.goodsInfo.pageNum eq requestScope.goodsInfo.pages}">
										<li><a  title="下一页">&rsaquo;</a></li>
										<li><a  title="末页">&raquo;</a></li>
									</c:if>
									<c:if test="${requestScope.goodsInfo.pageNum ne requestScope.goodsInfo.pages}">
										<li><a href="javaScript:changePage(${requestScope.goodsInfo.nextPage})" title="下一页">&rsaquo;</a></li>
										<li><a href="javaScript:changePage(${requestScope.goodsInfo.pages})" title="末页">&raquo;</a></li>
									</c:if>
									<li>
										&nbsp;&nbsp;总记录 ${requestScope.goodsInfo.total} 条
										&nbsp;&nbsp;总页数 ${requestScope.goodsInfo.pages} 页
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
	<script>
		if ("${success}" != ""){
			layer.alert("${success}");
		}

		function changePageNum() {
			let pageNumber = document.getElementById("number").value;
			let pageSize = document.getElementById("pageSize").value;
			window.location.href = "${pageContext.request.contextPath}/manage/goods?opr=queryGoodsList&pageNum="+pageNumber+"&pageSize="+pageSize;
		}

		function changePage(pageNumber){
			let goodName = document.getElementById("goodName").value;
			let goodTypeId = document.getElementById("goodTypeId").value;
			let pageSize = "${pageSize}"
			window.location.href = "${pageContext.request.contextPath}/manage/goods?opr=queryGoodsList&pageNum="+pageNumber+"&goodName="+goodName+"&goodTypeId="+goodTypeId+"&pageSize="+pageSize;
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