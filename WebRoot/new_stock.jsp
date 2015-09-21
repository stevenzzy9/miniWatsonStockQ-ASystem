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

		<title>|新股信息</title>
		<link href="css/index.css" rel="stylesheet" type="text/css" />

		<link href="css/xingu.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript" src="js/tabs.js"></script>

	

	</head>


	<body>
		<%@ include file="/header.jsp"%>
		<input type="hidden" id="for_tab_input" value="2" />
		<center>
		<div class="result">

			<div class="title_100">

				<h2>
					新股
				</h2>
			</div>

			<div class="words_xingu">
				<s:property value="content" escape="false" />
			</div>

		</div>

		<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>
