<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="index_product_sort.jsp" %>
	</div>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
			
				<li>
					<dl>
						<dt>标题：1</dt>
						<dd class="author">作者：2</span></dd>
						<dd>内容：3</dd>
					</dl>
				</li>
				
				<li>
					<dl>
						<dt>标题：1</dt>
						<dd class="author">作者：2</span></dd>
						<dd>内容：3</dd>
					</dl>
				</li>
				
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="#">上一页</a></li>					
					<li>2</li>
					<li class="current"><a href="#">3</a></li>
					
					<li><a href="#">下一页</a></li>
				</ul>
				
			</div>
			<div id="reply-box">
				<form action="#" method="post">
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" /></td>
						</tr>
						<tr>
							<td class="field">留言标题：</td>
							<td><input class="text" type="text" name="guestTitle" /></td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 上海海文 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
