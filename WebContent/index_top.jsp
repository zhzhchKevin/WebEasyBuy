
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	double num = Math.random();
 %>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	
	<div class="help"><a href="cartview" class="shopping">购物车</a>
	
	<c:if test="${sessionScope.userBack==null}">
	<a href="login.jsp" >登录</a>
	</c:if>
    <c:if test="${sessionScope.userBack!=null}">
    <a href="LoginOutServlet">退出</a>
    </c:if>
	
	
	<a href="register.jsp">注册</a>
	
    <a href="char-room.jsp">留言</a></div>
		
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="IndexServlet">首页</a></li>
			
			<li><a href="#">商品名称</a></li>
			
		</ul>
	</div>
	


</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
		
			<li class="first"><a href="#">商品名称</a></li>
		
		</ul>
	</div>
	<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v2.jiathis.com/code/jiathis_r.js?move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->
</div>
