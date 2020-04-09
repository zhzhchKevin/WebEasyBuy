
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.ithwua.IService.IProductService" %>
<%@ page import="com.ithwua.ServiceImpl.ProductServiceImpl" %>
<%@ page import="com.ithwua.bean.Product" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>

</head>
<body>
<%@ include file="index_top.jsp"  %>

<!-- 最近浏览 -->

<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="index_product_sort.jsp"%>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				
				<c:forEach items="${requestScope.pastProducts}" var="pastProduct">
					<dt><img style="width: 54px; height: 54px;" src="${pastProduct.fileName}" /></dt>
					<dd><a href="productview?id=${pastProduct.id}">${pastProduct.name}</a></dd>
		        </c:forEach>	
			
			</dl>
		</div>
	</div>
	
	
<!-- 特价商品 -->

	<div class="main">
		<div class="price-off">
			<h2>今日特价</h2>
			<ul class="product clearfix">

			<c:forEach items="${requestScope.products}" var="product">
				<li>
					<dl>
						<dt><a href="productview?id=${product.id}" target="_self"><img src="${product.fileName}" /></a></dt>
						<dd class="title"><a href="productview?id=${product.id}" target="_self">${product.name}</a></dd>
						<dd class="price">${product.price}</dd>
					</dl>
				</li>
		    </c:forEach>	

		 </ul>

<!-- 翻页按钮	 -->			
	
		
		<div class="pager">
			<ul class="clearfix">
				<li>
					<c:choose>
						<c:when test="${requestScope.nowPage==1}">
							<a href="IndexServlet?nowPage=1">上一页</a>
						</c:when>
						<c:otherwise>
							<a href="IndexServlet?nowPage=${requestScope.nowPage-1}">上一页</a>
						</c:otherwise>
		             </c:choose>
	            </li>
				
					
<!-- 					<li>2</li>

					<li><a href="#">3</a></li> -->
				
				

				<li>
					<c:choose>
						<c:when test="${requestScope.nowPage==requestScope.pageCount}">
							<a href="IndexServlet?nowPage=${requestScope.pageCount}">下一页</a> 
						</c:when>
						
						<c:otherwise>
							<a href="IndexServlet?nowPage=${requestScope.nowPage+1}">下一页</a> 
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
   </div>
	
	
<!-- 热卖推荐 -->	
		<div class="side">
			<%@ include file="index_news.jsp" %>	
		</div>
	
	
		<div class="spacer clear"></div> 
  		<div>
  		<p>热卖推荐</p>		
	        <%@ include file="hotproduct.jsp" %>
	    </div> 

	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 上海海文 All Rights Reserved.
</div>
</body>
</html>

