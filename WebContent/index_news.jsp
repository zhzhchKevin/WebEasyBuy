<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ithwua.bean.New" %>
<%@ page import="com.ithwua.IService.INewService" %>
<%@ page import="com.ithwua.ServiceImpl.newServiceImpl" %>
<div class="news-list">
<%
INewService newService=new newServiceImpl();
List<New> newslist=newService.queryAllNews();
request.setAttribute("newslist", newslist);
%>

	<h4>
		新闻动态
	</h4>
	
	<ul>
	     <c:forEach items="${newslist}" var="news">
			<li>
				<a href="news_view.jsp?title=${news.title}&content=${news.content}&createTime=${news.createTime}">${news.title}</a>
			</li>
		 </c:forEach>	
		 
	</ul>
</div>
<div class="news-list">
    <h4>
		新闻动态
	</h4>
	
	<ul>
	     <c:forEach items="${requestScope.newslist}" var="news">
			<li>
				<a href="newsViewServlet?title=${news.title}&content=${news.content}&createTime=${news.createTime}">${news.title}</a>
			</li>
		 </c:forEach>	
		 
	</ul>
</div>