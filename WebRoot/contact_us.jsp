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
								<a href="about_us.jsp">关于我们</a>
							</li>
							<li>
								<a href="notice.jsp">免责声明</a>
							</li>
							<li>
								<a href="contact_us.jsp"  class="current">联系我们</a>
							</li>
						</ul>
					</div>
					<div id="tabs" align="left">
						<div>
							<pre>


<h2>  			联系方式  </h2>
<br />
			    	总机：027-68778107<br />
  				电子邮件：stevenzzy9@gmail.com<br />
  				地址：中国湖北省武汉市洪山区珞瑜路37号 <br />
  				邮编：430079<br />
 				 <img width="367" height="280" src="images/关于我们_clip_image001.png" />
								<a name="_GoBack" id="_GoBack"></a>
								</p>









</pre>
						</div>
					</div>
				</div>


				<%@ include file="/footer.jsp"%>
		</center>
	</body>
</html>
