<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ithwua.IService.IProductService" %>
<%@ page import="com.ithwua.ServiceImpl.ProductServiceImpl" %>





 <style type="text/css" >
  #div5  
{position:relative;width:650px;height:210px;overflow:hidden; 
}
  #div6{position:absolute;}
  .hot li{float:left;list-style-type:none;padding:5px;}
  .hot img{border:none;}
  #div6 li a:hover{top:-10px;}
  a{position:relative;}
  </style>
 <script type="text/javascript" >
  window.onload=function()
  {
   var odiv2=document.getElementById('div6');
   var ali=odiv2.getElementsByTagName('li');
   var aspeed=-5;
   var timer=null;
   odiv2.innerHTML+=odiv2.innerHTML;
   odiv2.style.width=ali[0].offsetWidth*ali.length+'px';
odiv2.onmouseover=function(){clearInterval(timer);};
function a()
{
   timer=setInterval(function()    
     {
      odiv2.style.left=odiv2.offsetLeft+aspeed+'px';
      if (odiv2.offsetLeft<-odiv2.offsetWidth/2)
      {
       odiv2.style.left='0px';
       }
       },30);};
   odiv2.onmouseout=a;
   a();
   }
 </script>

<div id='div5'>
  <div id='div6'>
  
  
  
<%
IProductService productService=new ProductServiceImpl();

request.setAttribute("products", productService.queryAllProducts());

%>
  
<div class="hot">
			<h2>热卖推荐</h2>	
			<ul class="product clearfix">	
               
               <c:forEach items="${requestScope.products}" var="product">
				<li>
					<dl>
						<dt><a href="productview?id=${product.id}" target="_self"><img src="${product.fileName}" /></a></dt>
						<dd class="title"><a href="productview?id=${product.id}" target="_self">${product.name}</a></dd>
						<dd class="price">￥${product.price}</dd>
					</dl>
				</li>
              </c:forEach>	
              
			</ul>		
		</div>			
 </div>
 </div>