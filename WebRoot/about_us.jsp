<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>|免责声明</title>
		<link href="css/jingtai.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<center>
<a href="index.jsp"><div class="T_logo" ></div></a>
			<div id="search">
				<div id="change">
					<div id="nav">
						<ul type="none">
							<li>
								<a href="about_us.jsp" class="current">关于我们</a>
							</li>
							<li>
								<a href="notice.jsp">免责声明</a>
							</li>
							<li>
								<a href="contact_us.jsp">联系我们</a>
							</li>
						</ul>
					</div>
					<div id="tabs" align="left">
						<div>
							<pre>


<h2> 		团队介绍</h2>
				我们是一个平等的团队
				在这里，我们没有能力强弱的分别
				有的只是不同位置不同的责任
				所以我们会骄傲的介绍每一个成员

				我们是一个快乐的团队
				我们会一起工作、游戏和娱乐
				我们会一起吃饭、交流和争吵
				我们会一起努力、进步和探讨

				我们是一个平凡的团队
				我们这里没有天才
				我们都出身草根
				但是我们有共同的理想

				我们是一个强大的团队
				因为
				我们知道
				solid的每一步
				都凝聚了所有人的汗水和努力





	<h2>		Solid团队成员：</h2>
				张震宇	男	武汉大学	国际软件学院
				武斌	男	武汉大学	计算机学院
				任梦菲	女	武汉大学	国际软件学院
				李宗枝	男	武汉大学	国际软件学院
				王鹏	男	武汉大学	国际软件学院
				李春典	男	武汉大学	国际软件学院
				孟蒙	女	武汉大学	经济与管理学院
				王萍丽	女	武汉大学	经济与管理学院
				卞正野	男	武汉大学	经济与管理学院






</pre>
						</div>
					</div>
				</div>


				<%@ include file="/footer.jsp" %>
		</center>
	</body>
</html>
