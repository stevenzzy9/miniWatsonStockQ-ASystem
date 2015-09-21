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
		$('div.B_r img[src$=".gif"]').wrap("<center></center>");
		$('div.B_r img[src$=".jpg"]').wrap("<center></center>");
	});

</script>
	</head>

	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<input type="hidden" id="for_tab_input" value="1" />
			<div class="result">
					<div id="link">
						<a href="baike?name=<s:property value="name" />" ><h1>
								<s:property value="name" /> &nbsp;&nbsp;百科
							</h1> </a>
					</div>
					<s:iterator value="baikes" status="st" id="baikes">
                    <div class="B_r">
						<div class="title_100">
							<h2>
								<s:property value="title" escape="false"/>
							</h2>
						</div>
						<div class="words_more">
							<pre><s:property value="answer" escape="false" />
							</pre>

						</div>

</div> 
					</s:iterator>
			</div>
			<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>