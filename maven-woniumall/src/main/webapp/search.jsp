<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
	$(function() {
		$('.allsort').hover(function() {
			$('#div_allsort').show();
		}, function() {
			$('#div_allsort').hide();
		});
	});
</script>
<style>
	.menu:hover sortlist{
		display: block;
	}
</style>
<div class="searchbar">
	<div class="allsort">
		<a href="${base}" class="menu" id="menu">全部商品分类</a>

		<!--总的商品分类-开始-->
		<ul class="sortlist" id='div_allsort' style='display:none'>
			<c:forEach var="categotry" items="${categoryListForAll}">
				<li><a href="index?opr=viewGoodsListByCategotry&categotryId=${categotry.id}">${categotry.name}</a></li>
			</c:forEach>
		</ul>
	</div>

	<div class="searchbox">
		<form method='get' action='index'>
			<input type='hidden' name='opr' value='viewGoodsListByGoodsName' /> <input
				type='hidden' name='action' value='search_list' /> <input
				class="text" type="text" name='goodsName' autocomplete="off"
				value="输入关键字..." /> <input class="btn" type="submit" value="商品搜索"
				onclick="checkInput('word','输入关键字...');" />
		</form>

	</div>
	<div class="hotwords">热门搜索：</div>
</div>
<script>
	//显示二级菜单
	document.getElementById("menu").addEventListener("mousemove",function(){
		document.getElementById("div_allsort").style.display = "block";
	});
	document.getElementById("div_allsort").addEventListener("mouseleave",function(){
		document.getElementById("div_allsort").style.display = "none";
	});


</script>