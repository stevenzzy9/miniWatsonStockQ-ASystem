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

		<title>|人物介绍</title>

		<link href="css/index.css" rel="stylesheet" type="text/css" />


		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript" src="js/tabs.js"></script>

	</head>


	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<input type="hidden" id="for_tab_input" value="3" />
			<div class="result">

				<s:iterator value="answerCate" status="st" id="answerCate">
					<div id="link">
					<a href="investor?name=<s:property value="key" /> ">
								<h1>
									<s:property value="key" />
									&lt;&lt;点击查看人物详细信息
								</h1> </a>
					</div>
<div class="B_r">
					<div class="title_100">

						<h2>
							<s:property value="question" />
						</h2>
					</div>

					<div class="words_more">

						<div id="biankuang">
							<img id="touxiang" src="images/55a3322fad32dcb0ea8d311d9.jpg"
								alt="fafa" />
						</div>

						<pre>

 未在搜索人物中找到指定的信息，我们建议您进行以下操作：
 
        ①点击左侧的人名链接，进入人物页面查找所需信息
        ②修改搜索的关键词以达到好的搜索结果

</pre>


					</div>
 </div>

				</s:iterator>

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
