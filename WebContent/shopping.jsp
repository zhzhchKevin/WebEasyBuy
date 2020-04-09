<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script type="text/javascript">
$(function(){
		$("dl").each(function(){
			//点击后修改数量
			$("dd",this).click(function(){
				
				if($(this).prev().find("input").val()<=0){
					alert("数量不能小于1");
					$(this).prev().find("input").val(1);
					
				}else{
					
					$.ajax({
						url:"UpdateCartServlet",
						
						data:{
						"cartId":$(this).prev().find("input").attr("cartId"),
						"quantity":$(this).prev().find("input").val()
						},
						
						type:"post",
						
						dataType:"html",
						
						success:function(data){
								if(data==0){
									alert("修改失败！！！");
								}else{
									alert("修改成功！！！");
								}
						},
						error:function(){
							alert("请求数据失败！！！");
						}
					});
				    
				    }
				});			
		});	
			
			$(".items").each(function(){
			
			//点击后删除数量
			$(".delete",this).click(function(){
				$.ajax({
					url:"DeleteCartServlet",
					data:{
					"cartId":$(this).attr("cartId"),
					},
					type:"post",
					dataType:"html",
					success:function(data){

							if(data==0){
								alert("删除失败！！！");
							}else{
								alert("删除成功！！！");
								$(this).parent().remove();
								window.location.reload();
							}
					},
					error:function(){
						alert("请求数据失败！！！");
					}
				});
			});
		});
			
			
});


           
</script>

</head>
<body>
<%@ include file="index_top.jsp"  %>

<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="DoBuyServlet" method="post">
			<table>
				<tr>
				    <th>加入订单</th>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				
			<c:forEach items="${requestScope.carts}" var="cart">
		             <tr id="product_id_1" class="items">
		             
		                <td><input name="items" type="checkbox" value="${cart.id}"/></td>
		                
		                <!--  显示图片和价格 -->
						<td class="thumb"><img style="width: 100px; height: 100px;" src="${cart.fileName}" /><a href="#">${cart.productName}</a></td>
						<td class="price" id="price_id_1">
							￥<span id="span_1">${cart.productPrice}</span>
							<input type="hidden" id="subPrice" value="" />
						</td>
						
						<!-- 修改操作 -->
						<td class="number">
							<dl>
								<dt><input cartId="${cart.id}" type="text" name="number" value="${cart.quantity}" /></dt>
								<dd class="update" >修改</dd>
							</dl>
						</td>
						
						
						<td class="delete" cartId="${cart.id}"><a href="#">删除</a></td>
					 </tr>
		    </c:forEach>	

			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2016 上海海文 All Rights Reserved.
</div>
</body>
</html>

