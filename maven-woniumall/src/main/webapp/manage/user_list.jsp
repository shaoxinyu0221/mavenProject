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
				<li class="active">你现在的位置是：用户管理</li>
				<li><a href="#">用户列表</a></li>
			</ol>
		</div>
		<!-- 路径导航结束 -->
		<div style="padding-left: 10px; padding-right: 10px;">
			<!-- 多条件查询 -->
				<form class="form-inline" id="myform" action="${pageContext.request.contextPath}/manage/user" >
					<div class="form-group">
						<label for="userAccount">用户账号：</label>
						<input type="text" class="form-control" name="userAccount" id="userAccount" placeholder="请输入用户账号" value="${userAccount}">
					</div>
					<div class="form-group">
						<label >用户状态：</label>
						<select name="userStatus" class="form-control" id="userStatus">
							<option value="">---请选择---</option>
							<option value="0"
									<c:if test="${userStatus eq '0'}">selected</c:if>
							>正常用户</option>
							<option value="1"
									<c:if test="${userStatus eq '1'}">selected</c:if>
							>被封禁用户</option>
							<option value="2"
									<c:if test="${userStatus eq '2'}">selected</c:if>
							>未激活用户</option>
						</select>
						<input type="hidden" name="opr" value="gotoUserList">
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
						<th class="col-md-1 text-center">用户名</th>
						<th class="col-md-1 text-center">注册时间</th>
						<th class="col-md-1 text-center">头像</th>
						<th class="col-md-1 text-center">积分</th>
						<th class="col-md-1 text-center">邮箱</th>
						<th class="col-md-1 text-center">余额</th>
						<th class="col-md-1 text-center">状态</th>
						<th class="col-md-2 text-center">操作</th>
					</tr>
				</thead>
				<tbody id="tbd">
					<c:forEach var="user" items="${userInfo.list}" varStatus="vs">
						<tr class="text-center">
							<td>${vs.count + requestScope.userInfo.startRow-1}</td>
							<td><input type="checkbox" name="chk" id="chk" onclick="selectChk()"/></td>
							<td>${user.account}</td>
							<td>${user.regTime}</td>
							<td><img src="${user.avatar}"/></td>
							<td>${user.score}</td>
							<td>${user.email}</td>
							<td>${user.money}</td>
							<td>
                                <c:if test="${user.status eq '0'}">
                                    正常
                                </c:if>
                                <c:if test="${user.status eq '1'}">
                                    被禁用
                                </c:if>
                                <c:if test="${user.status eq '2'}">
                                    未激活
                                </c:if>
                            </td>
							<td>
								<a href="manage/user?opr=userBan&userId=${user.id}&updateStatus=${user.status}"><span class="glyphicon glyphicon-pencil"></span>
                                    <c:if test="${user.status eq '0'}">
										<span style="color:red">锁定用户</span>
                                    </c:if>
                                    <c:if test="${user.status eq '1'}">
										<span style="color:green">解锁用户</span>
                                    </c:if>
									<c:if test="${user.status eq '2'}">
										<span style="color:#d58512">激活用户</span>
									</c:if>
                                </a>&nbsp;&nbsp;&nbsp;
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
									<c:if test="${requestScope.userInfo.pageNum eq 1}">
										<li><a  title="首页">&laquo;</a></li>
										<li><a  title="上一页">&lsaquo;</a></li>
									</c:if>
									<c:if test="${requestScope.userInfo.pageNum ne 1}">
										<li><a href="javascript:changePage(1)" title="首页">&laquo;</a></li>
										<li><a href="javascript:changePage(${requestScope.userInfo.prePage})" title="上一页">&lsaquo;</a></li>
									</c:if>

									<c:forEach var="pageNum" items="${requestScope.userInfo.navigatepageNums}">
										<li class="<c:if test="${pageNum eq requestScope.userInfo.pageNum}">active</c:if>">
											<a href="javascript:changePage(${pageNum})">${pageNum}</a>
										</li>
									</c:forEach>

									<c:if test="${requestScope.userInfo.pageNum eq requestScope.userInfo.pages}">
										<li><a  title="下一页">&rsaquo;</a></li>
										<li><a  title="末页">&raquo;</a></li>
									</c:if>
									<c:if test="${requestScope.userInfo.pageNum ne requestScope.userInfo.pages}">
										<li><a href="javaScript:changePage(${requestScope.userInfo.nextPage})" title="下一页">&rsaquo;</a></li>
										<li><a href="javaScript:changePage(${requestScope.userInfo.pages})" title="末页">&raquo;</a></li>
									</c:if>
									<li>
										&nbsp;&nbsp;总记录 ${requestScope.userInfo.total} 条
										&nbsp;&nbsp;总页数 ${requestScope.userInfo.pages} 页
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
			window.location.href = "${pageContext.request.contextPath}/manage/user?opr=gotoUserList&pageNum="+pageNumber+"&pageSize="+pageSize;
		}

		function changePage(pageNumber){
			let userAccount = document.getElementById("userAccount").value;
			let userStatus = document.getElementById("userStatus").value;
			let pageSize = "${pageSize}"
			window.location.href = "${pageContext.request.contextPath}/manage/user?opr=gotoUserList&pageNum="+pageNumber+"&userAccount="+userAccount+"&userStatus="+userStatus+"&pageSize="+pageSize;
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