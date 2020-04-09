<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script type="text/javascript">
$(function(){
			//点击放入购物车
			$("#intocart").click(function(){
			
				$.ajax({
					url:"AddCartServlet",
					data:{
					"productId":"${requestScope.product.id}"
					},
					
					type:"post",
					
					dataType:"html",
					
					success:function(data){

							if(data==1){
								alert("购买失败！！！");
							}else if(data==2){
								alert("已经成功放入购物车！！！");
							}else if(data==0){
								alert("你尚未登录，请先登录！")
								window.location.replace("IndexServlet");
							}
					},
					error:function(){
						alert("请求数据失败！！！");
					}
				});
			});
});
</script>

</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="ProductServlet">易买网</a> &gt; <a href="product-list.html">图书音像</a> &gt; 图书
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="index_product_sort.jsp" %>
	</div>
	<div id="product" class="main">
		<h1>${requestScope.product.name}</h1>
		<div class="infos">
			<div class="thumb"><img style="width: 100px; height: 100px;" src="${requestScope.product.fileName}" /></div>
			<div class="buy">
				<p>商城价：<span class="price">${requestScope.product.price}</span></p>
				
				<p>库　存：
				<c:if test="${requestScope.product.stock==0}">无货</c:if>
				<c:if test="${requestScope.product.stock!=0}">有货</c:if>
				</p>
				
				<div class="button"><input type="button" name="button" value="" onclick="" /><a id="intocart" href="#" productId="${requestScope.product.id}">放入购物车</a></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				商品名字：${requestScope.product.name}<br />
				商品描述：${requestScope.product.description}<br />
				商品价格：${requestScope.product.price}<br />
				商品库存：${requestScope.product.stock}<br />
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 上海海文 All Rights Reserved.
</div>
</body>
</html>

