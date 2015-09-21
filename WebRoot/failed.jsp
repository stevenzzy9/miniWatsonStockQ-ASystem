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
		<title>|百科知识</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/index.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript" src="js/tabs.js"></script>

		<script type="text/javascript">
	$(document).ready(function() {

		$("#quanbu_button").click(function() {

			$("#quanbu_form").submit();

		});

		$("#jichu_button").click(function() {

			$("#jichu_form").submit();

		});

		$("#gegu_button").click(function() {

			$("#gegu_form").submit();

		});

		$("#renwu_button").click(function() {

			$("#renwu_form").submit();

		});

		$("#caozuo_button").click(function() {

			$("#caozuo_form").submit();

		});

		$("#xinwen_button").click(function() {

			$("#xinwen_form").submit();

		});

	});

</script>
	</head>

	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<div class="result">
				查找失败
			</div>
			<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>