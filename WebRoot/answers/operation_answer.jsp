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
$(function(){
	  var h=[];
	  
	    $("div.words_slide").each(function(i){
			h[i]=$(this).height();
			});
	   
	   
	  $(".words_slide").height("80px");
	  $(".c_slide").click().toggle(function(){
		        var index=$(this).index(".c_slide");
			  $(this).prev(".words_slide").animate({height:h[index]});
			  $(this).html("<<收起");
			  },
			  function(){
			  var index=$(this).index(".c_slide");
			  window.scrollBy(0,80-h[index]);
			  $(this).prev(".words_slide").animate({height:"80"},function(){
			  	
			  });
			  $(this).html(">>显示全部");
	      });


	  });
</script>
	</head>

	<body>
		<center>
			<%@ include file="/header.jsp"%>
			<input type="hidden" id="for_tab_input" value="4" />
			<div class="result">
				<s:if test="flag==0">
					<s:iterator value="answers" status="st" id="answers">
						<div class="B_r">
						<div class="title_100">

							<h2>
								<s:property value="question" />
							</h2>
						</div>

						<div class="words_slide">
                        <pre><s:property value="answer" />
                            </pre>
						</div>
						<div class="c_slide" style="font-size:0.8em; color:#888;">
>>显示全部
</div>
						</div>
					</s:iterator>
				</s:if>
				
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
