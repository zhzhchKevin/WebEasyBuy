<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script>

$(function(){
			$("#userName").blur(function(){
				
					$.ajax({
						url:"UserNameServlet",
						
						data:{
						"userName":$(this).val()
						},
						
						type:"post",
						
						dataType:"html",
						
						success:function(data){
								if(data==0){
									$("#uName").html("用户名重复，不可用");
									$("#uName").show(10);
								}
						},
						error:function(){
							alert("请求数据失败！！！");
						}
					});
		});	
			
			
			
			$("#email").blur(function(){
				
				$.ajax({
					url:"EmailServlet",
					
					data:{
					"email":$(this).val()
					},
					
					type:"post",
					
					dataType:"html",
					
					success:function(data){
							if(data==0){
								$("#Email").html("邮箱已经被使用");
								$("#Email").show(10);
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
<%= "1+4" %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册易买网</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="RegisterServlet">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input id="userName" class="text" type="text" name="userName" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="uName"></span><span id="uName2"></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" name="rePassWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td ><input type="radio" name="sex"  style="border:0px;" checked="checked" value="0" />男<input type="radio" name="sex" style="border:0px;" value="1"/>女<span></span></td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input class="text" type="text" name="birthday" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">身份证：</td>
						<td><input class="text" type="text" name="identity" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">电子邮件：</td>
						<td><input id="email" class="text" type="text" name="email" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="Email"></span></td>
					</tr>
					<tr>
						<td class="field">手机：</td>
						<td><input class="text" type="text" name="mobile" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">地址：</td>
						<td><input class="text" type="text" name="address" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><img id="veryCode" src="code.jsp" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 上海海文 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

