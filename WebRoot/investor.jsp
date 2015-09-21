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

		<title>|投资家百科</title>

		<link href="css/index.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript" src="js/tabs.js"></script>


	</head>


	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<input type="hidden" id="for_tab_input" value="3" />

			<div class="result">

				<div id="link">
					<a href="investor?name=<s:property value="name" /> ">
						<h1>
							<s:property value="name" />
							百科
						</h1> </a>
				</div>
				<s:if test="flags[0]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						简介
					</h2>
				</div>
				<div class="words_more">
                 <pre><div id="biankuang"><img id="touxiang" src="image/investor/<s:property value="name" escape="false" />.jpg" alt="<s:property value="name" escape="false" />" /></div><s:property value="investor.introduction" escape="false" />
                    </pre>
				</div>
</div> 
</s:if>
<s:if test="flags[1]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						投资风格
					</h2>
				</div>
				<div class="words_more">
                 <pre><s:property value="investor.style" escape="false" />
                    </pre>
				</div>
</div> 
</s:if>
<s:if test="flags[2]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						名言
					</h2>
				</div>
				<div class="words_more">
                 <pre><s:property value="investor.saying" escape="false" />
                    </pre>
				</div>
</div> 
</s:if>
<s:if test="flags[3]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						大事记
					</h2>
				</div>
				<div class="words_more">
                 <pre><s:property value="investor.event" escape="false" />
                    </pre>
				</div>
</div> 
</s:if>
<s:if test="flags[4]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						著书
					</h2>
				</div>
				<div class="words_more">
                 <pre><s:property value="investor.books" escape="false" />
                    </pre>
				</div>
</div> 
</s:if>
<s:if test="flags[5]==1" >
<div class="B_r">
				<div class="title_100">
					<h2>
						<s:property value="name" />
						领域
					</h2>
				</div>
				<div class="words_more">
                <pre><s:property value="investor.field" escape="false" />
                    </pre>
                    </div>

</div> 
</s:if>

				</div>
			</div>

			<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>
