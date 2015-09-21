<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>|股票操作</title>
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/tabs.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
	$(function() {
		$(".title_100").bind("click", function() {
			if ($(this).next("div.words_slide").is(":visible")) {
				$(this).next("div.words_slide").slideUp("slow");
			} else {
				$(this).next("div.words_slide").slideDown("slow");
			}
		})
	});
</script>
	</head>

	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<input type="hidden" id="for_tab_input" value="4" />
			<div class="result">
            						<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="question" />
					</h2>
				</div>

				<div class="words_more">
					<pre>

 未在搜索股票操作中找到指定的信息，我们建议您进行以下操作：
 
        ①修改搜索的关键词以达到好的搜索结果
        ②查看一下的google返回给您的结果

</pre>

				</div>
				</div>
				<div class="g_r">	
<br/>
<div id="link">
	<h2>
		以下是google搜索的结果摘要：
	</h2>
</div>
<br/>
<br/>
<div class="B_r">
	<br /><br />
	<img id="loadImage" src="images/loadImage.gif" />
</div>	
</div>		
			</div>

			<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>
