<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<script type="text/javascript">
$(function(){
	$(".g_r>.B_r").load("GetGooResult?question=<s:property value="question" escape="false" />",function(){$(this).hide().fadeIn(1000);});
	var $tabIndex = $("#for_tab_input").val();
	$("#nav ul").tabs("#tabs>div", {
		effect : 'fade',
		fadeOutSpeed : 400,
		initialIndex:parseInt($tabIndex)
	});
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
</script>
<a href="index.jsp"><div class="T_logo" ></div></a>
<div id="search">
	<div id="change">
		<div id="nav">
			<ul type="none">
				<li class="blank"></li>
				<li>
					<a href="#1">全部</a>
				</li>
				<li>
					<a href="#2">基础知识</a>
				</li>
				<li>
					<a href="#3">个股信息</a>
				</li>
				<li>
					<a href="#4">人物介绍</a>
				</li>
				<li>
					<a href="#5">股票操作</a>
				</li>
				<li>
					<a href="#6">公司新闻</a>
				</li>
			</ul>
		</div>
		<div id="tabs">
			<div id="moren" style="display: block;" name="全部">
				<div class="mid">
					<form id="quanbu_form" name="str" action="answer" method="post">
						<input id="quanbu_input" style="" class="search" name="question"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;请输入您所要查找的金融问题....."
							value="<s:property value="question" escape="false"/>" />
					</form>
				</div>
				<input type="submit" id="quanbu_button" class="button" value="" />
				<br />
			</div>

			<div id="jichu" style="display: none;" name="基础知识">

				<div class="mid">

					<form id="jichu_form" name="str" action="answer" method="post">

<input type="hidden" name="method" value="1"/>
						<input id="jichu_input" style="" class="search" name="question"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;在这里可以查询相关的基础知识....."
							value="<s:property value="question" escape="false"/>" />
					</form>
				</div>

				<input type="submit" id="jichu_button" class="button" value="" />
			</div>
			<div style="display: none;" name="个股信息">
				<div class="mid">
					<form id="gegu_form" name="str" action="answer" method="post">
<input type="hidden" name="method" value="2"/>
						<input id="gegu_input" style="" class="search" name="question"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;在这里可以查询个股信息....."
							value="<s:property value="question" escape="false"/>" />
					</form>
				</div>

				<input type="submit" id="gegu_button" class="button" value="" />

				<div class="gegu_qita">

					<a href="ScanNewShare">新股</a>

					<a href="GetCompanys">地域行业</a>
				</div>

			</div>

			<div id="renwu" style="display: none;" name="人物介绍">

				<div class="mid">

					<form id="renwu_form" name="str" action="answer" method="post">
<input type="hidden" name="method" value="3"/>
						<input id="renwu_input" style="" class="search" name="question"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询著名的投资人....."
							value="<s:property value="question" escape="false"/>" />
					</form>
				</div>
				<input type="submit" id="renwu_button" class="button" value="" />



			</div>

			<div style="display: none;" name="股票操作">

				<div class="mid">

					<form id="caozuo_form" name="str" action="answer" method="post">
<input type="hidden" name="method" value="4"/>
						<input id="caozuo_input" style="" class="search" name="question"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询股票的操作知识....."
							value="<s:property value="question" escape="false"/>" />
					</form>
				</div>

				<input type="submit" id="caozuo_button" class="button" value="" />



			</div>



			<div style="display: none;" name="公司新闻">

				<div class="mid">

					<form id="xinwen_form" name="str" action=SearchNews method="post">

						<input id="xinwen_input" style="" class="search" name="company"
							watermark="&nbsp;&nbsp;&nbsp;&nbsp;这里可以查询股票的操作知识....."
							value="<s:property value="question"/>" />
					</form>
				</div>

				<input type="button" id="xinwen_button" class="button" />
			</div>
		</div>

	</div>