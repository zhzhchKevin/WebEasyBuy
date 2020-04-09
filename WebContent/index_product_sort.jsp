<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ithwua.IService.ICategoryService" %>
<%@ page import="com.ithwua.ServiceImpl.CategoryServiceImpl" %>
<%@ page import="com.ithwua.bean.Category" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 左端的商品分类栏 -->


<div class="box">
<%
ICategoryService categoryService=new CategoryServiceImpl();
List<Category> parentCategorys=categoryService.queryALLParentCategorys();
request.setAttribute("parentCategorys", parentCategorys);

List<Category> allCategorys=categoryService.queryALLCategorys();
request.setAttribute("allCategorys", allCategorys);

%>



	<h2>
		商品分类
	</h2>
	<dl>
		 	
		<c:forEach items="${requestScope.parentCategorys}" var="parent"> 
		    
		<dt>${parent.name}</dt>
		
		<c:forEach items="${requestScope.allCategorys}" var="child"> 
		
		<c:if test="${child.parentId==parent.id&&child.parentId!=child.id}">
				<dd>
					<a href="product_list.jsp?categoryId=${child.id}&categoryName=${child.name}">${child.name}</a>
				</dd>
		</c:if>
				
        </c:forEach>


		</c:forEach>
		
	</dl>
</div>
