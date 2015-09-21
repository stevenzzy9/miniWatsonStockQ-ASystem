<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SolidQA</title>
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/watermark.js"></script>
		<script type="text/javascript" src="js/tabs.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
		$("#str").fn;
		$("#quanbu_button").click(function() {
		       if($("#quanbu_input").val()){
				$("#quanbu_form").submit();
		}
			});
			$("#jichu_button").click(function() {
				if($("#jichu_input").val()){
				$("#jichu_form").submit();
				}
			});
			$("#gegu_button").click(function() {
				if($("#gegu_input").val()){
				$("#gegu_form").submit();
				}
			});
			$("#renwu_button").click(function() {
				if($("#renwu_input").val()){
				$("#renwu_form").submit();
				}
			});
			$("#caozuo_button").click(function() {
				if($("#caozuo_input").val()){
				$("#caozuo_form").submit();
				}
			});
			$("#xinwen_button").click(function() {
				if($("#xinwen_input").val()){
				$("#xinwen_form").submit();
				}
			});
	});
	$(function() {
		$("#nav ul").tabs("#tabs>div", {
			effect : 'fade',
			fadeOutSpeed : 400
		});
	});
</script>
	</head>
	<body>
		<center>

			<div id="search">
				<div id="change">
					<div id="nav">
						<ul type="none">
							<li>
								<a href="k1" class="current">全部</a>
							</li>
							<li>
								<a href="k2" class>基础知识</a>
							</li>
							<li>
								<a href="k3" class>个股信息</a>
							</li>
							<li>
								<a href="k4" class>人物介绍</a>
							</li>
							<li>
								<a href="k5" class>股票操作</a>
							</li>
							<li>
								<a href="k6" class>公司新闻</a>
							</li>
						</ul>
					</div>
					<div id="tabs">
						<div id="moren" style="display: block;" name="全部">
							<br />
							<div id="logo"></div>
							<div class="mid">
								<form id="quanbu_form" name="str" action="answer" method="post">
								<input type="hidden" value="1" name="type" />
									<input id="quanbu_input" style="" class="search" name="question"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;请输入您所要查找的金融问题....." />
								</form>
							</div>
							<input type="button" id="quanbu_button" class="button" />
						</div>
						<div id="jichu" style="display: none;" name="基础知识">
							<div id="stay"></div>
							<div class="mid">
								<form id="jichu_form" name="str" action="answer" method="post">
								<input type="hidden" value="1" name="method" />
									<input id="jichu_input" style="" class="search" name="question"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;在这里可以查询相关的基础知识....." />
								</form>
							</div>
							<input type="button" id="jichu_button" class="button" />

						</div>
						<div id="gegu" style="display: none;" name="个股信息">
							<div id="stay"></div>
							<div class="mid">
								<form id="gegu_form" name="str" action="answer" method="post">
								<input type="hidden" value="2" name="method" />
									<input id="gegu_input" style="" class="search" name="question"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;在这里可以查询个股信息....." />
								</form>
							</div>
							<input type="button" id="gegu_button" class="button" />
							<div class="gegu_qita">
								<a  href="ScanNewShare">新股</a>
								<a href="GetCompanys">地域行业</a>
							</div>
						</div>
						<div id="renwu" style="display: none;" name="人物介绍">
							<div id="stay"></div>
							<div class="mid">
								<form id="renwu_form" name="str" action="answer" method="post">
								<input type="hidden" value="3" name="method" />
									<input id="renwu_input" style="" class="search" name="question"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询著名的投资人....." />
								</form>
							</div>
							<input type="button" id="renwu_button" class="button" />


						</div>
						<div id="caozuo" style="display: none;" name="股票操作">
							<div id="stay"></div>
							<div class="mid">
								<form id="caozuo_form" name="str" action="answer" method="post">
								<input type="hidden" value="4" name="method" />
									<input id="caozuo_input" style="" class="search" name="question"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询股票的操作知识....." />
								</form>
							</div>
							<input type="button" id="caozuo_button" class="button" />
						</div>

						<div id="xinwen" style="display: none;" name="公司新闻">
							<div id="stay"></div>
							<div class="mid">
								<form id="xinwen_form" name="str" action="SearchNews" method="post">
								<input type="hidden" value="6" name="type" />
									<input id="xinwen_input" style="" class="search" name="company"
										watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询公司相关新闻....." />
								</form>
							</div>
							<input type="button"  id="xinwen_button" class="button" />
						</div>
					</div>
				</div>
			</div>


			<%@ include file="footer.jsp" %>
		</center>
	</body>
</html>
