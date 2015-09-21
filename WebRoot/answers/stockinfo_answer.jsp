<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>|个股信息</title>
    
	<link href="css/index.css" rel="stylesheet" type="text/css" />


		<script type="text/javascript" src="js/jquery.js"></script>

		<script type="text/javascript" src="js/tabs.js"></script>
		

	</head>
  
  <body>
    <center>
    <%@ include file="/header.jsp"%>
    <input type="hidden" id="for_tab_input" value="2" />
    <div class="result">
				<s:iterator value="answerCate" status="st" id="answerCate">
					<div id="link">
						<a href="GetCompanyInfo?id=<s:property value="id" />" ><h1>
								<s:property value="key" />点击进入词条百科
							</h1> </a>
					</div>
					<s:iterator value="value" status="st" id="va">
					
                    <div class="B_r">
						<div class="title_100">
							<h2>
								<s:property value="question" />
							</h2>
						</div>

						<div class="words_more"><s:if test="answerType==1">
							<pre><s:property value="answer" escape="false" />
                            </pre>
                            </s:if>
                            <s:if test="answerType==12">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="10%">
										姓名
									</td>
									<td width="9%">
										性别
									</td>
									<td width="13%">
										职务
									</td>
									<td width="12%">
										学历
									</td>
									<td width="14%">
										年薪（万元）
									</td>
									<td width="15%">
										持股数（万股）
									</td>
									<td width="13%">
										任职起始日
									</td>
									<td width="14%">
										任职截止日
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#ffffff"
										onmouseout=" this.style.backgroundColor='#FFFFFF';"
										onmouseover=" this.style.backgroundColor='#fcfae7';">
										<td width="10%" align="center">
											<s:property value="name" />
										</td>
										<td width="9%" align="center">
											<s:property value="sex" />
										</td>
										<td width="13%" align="center">
											<s:property value="position" />
										</td>
										<td width="12%" align="center">
											<s:property value="educatioin" />
										</td>
										<td width="14%" align="right" style="padding-right: 5px;">
											<s:property value="salary" />
										</td>
										<td width="15%" align="right" style="padding-right: 5px;">
											<s:property value="shares" />
										</td>
										<td width="13%" align="center">
											<s:property value="officeholding" />
										</td>
										<td width="14%" align="center">
											<s:property value="positionend" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>

					<!-- answerType==10 :财务指标 -->
					<s:elseif test="answerType==10">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="20%">&nbsp;
										
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td width="17%">
											<s:property value="time" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股收益(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="eps" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股收益扣除(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="nseps" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股净资产(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="navps" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										调整后每股净资产(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="anaps" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										净资产收益率(%)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="roe" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股资本公积金(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="scf" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股未分配利润(元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="reps" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										主营业务收入(万元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="mbr" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										主营业务利润(万元)
									</td>
									<s:iterator value="answer" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="mbp" />
										</td>
									</s:iterator>
								</tr>
							</tbody>
						</table>
					</s:elseif>

					<!-- answerType==8" :分红配送 -->
					<s:elseif test="answerType==8">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="14%" rowspan="2">
										截止日
									</td>
									<td width="15%" rowspan="2">
										公告日
									</td>
									<td colspan="3">
										分配方案（每10股）
									</td>
									<td width="14%" rowspan="2">
										股权登记日
									</td>
									<td width="15%" rowspan="2">
										除权除息日
									</td>
								</tr>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="15%">
										分红额
									</td>
									<td width="14%">
										送股(股)
									</td>
									<td width="13%" align="right" style="padding-right: 5px;">
										转增(股)
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#ffffff"
										onmouseout=" this.style.backgroundColor='#FFFFFF';"
										onmouseover=" this.style.backgroundColor='#fcfae7';"
										style="background-color: rgb(255, 255, 255);">
										<td width="14%" align="center">
											<s:property value="endtime" />
										</td>
										<td width="15%" align="center">
											<s:property value="publishtime" />
										</td>
										<td width="15%" align="right" style="padding-right: 5px;">
											<s:property value="dividentamount" />
										</td>
										<td width="14%" align="right" style="padding-right: 5px;">
											<s:property value="bonusissue" />
										</td>
										<td width="13%" align="right" style="padding-right: 5px;">
											<s:property value="increment" />
										</td>
										<td width="14%" align="center">
											<s:property value="registerdate" />
										</td>
										<td width="15%" align="center">
											<s:property value="headline" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>

					<!-- answerType==11 :历史行情 -->
					<s:elseif test="answerType==11">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr align="center" bgcolor="#ecf5fe">
									<td width="11%"></td>
									<td width="11%">
										日期
									</td>
									<td width="11%">
										涨跌幅
									</td>
									<td width="11%">
										开盘价
									</td>
									<td width="11%">
										收盘价
									</td>
									<td width="11%">
										成交量
									</td>
									<td width="11%">
										成交额
									</td>
									<td width="11%">
										最高
									</td>
									<td width="11%">
										最低
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#f6f6f6" align="center">
										<td width="11%">
											<s:property value="#st.count" />
										</td>
										<td width="11%">
											<s:property value="date" />
										</td>
										<td width="11%">
											<span class="font_green"><s:property value="rosedrop" />
											</span>
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="openingprice" />
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="closingprice" />
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="tradingvolume" />
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="turnover" />
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="highest" />
										</td>
										<td width="11%" style="text-align: right;">
											<s:property value="lowest" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>

					<!-- answerType==11 :收入构成 -->
					<s:elseif test="answerType==11">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1" style="margin-bottom: 8px;">
							<tbody>
								<tr bgcolor="#fffdeb">
									<td colspan="6" style="font-weight: bold; color: #000000;">
										产品
									</td>
								</tr>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="11%">
										截止日
									</td>
									<td width="25%">
										项目名称
									</td>
									<td width="17%">
										主营业务收入(万元)
									</td>
									<td width="17%">
										主营业务成本(万元)
									</td>
									<td width="18%">
										主营业务利润(万元)
									</td>
									<td width="12%">
										毛利率(%)
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#ffffff">
										<td width="11%" align="center">
											<s:property value="endtime" />
										</td>
										<td width="25%" align="left" style="padding-left: 5px;">
											<s:property value="projectname" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="mbr" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="cost" />
										</td>
										<td width="18%" align="right" style="padding-right: 5px;">
											<s:property value="mbp" />
										</td>
										<td width="12%" align="right" style="padding-right: 5px;">
											<s:property value="grossprofit" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1" style="margin-bottom: 8px;">
							<tbody>
								<tr bgcolor="#fffdeb">
									<td colspan="6" style="font-weight: bold; color: #000000;">
										地区
									</td>
								</tr>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="11%">
										截止日
									</td>
									<td width="25%">
										项目名称
									</td>
									<td width="17%">
										主营业务收入(万元)
									</td>
									<td width="17%">
										主营业务成本(万元)
									</td>
									<td width="18%">
										主营业务利润(万元)
									</td>
									<td width="12%">
										毛利率(%)
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#ffffff">
										<td width="11%" align="center">
											<s:property value="endtime" />
										</td>
										<td width="25%" align="left" style="padding-left: 5px;">
											<s:property value="projectname" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="mbr" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="cost" />
										</td>
										<td width="18%" align="right" style="padding-right: 5px;">
											<s:property value="mbp" />
										</td>
										<td width="12%" align="right" style="padding-right: 5px;">
											<s:property value="grossprofit" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1" style="margin-bottom: 8px;">
							<tbody>
								<tr bgcolor="#fffdeb">
									<td colspan="6" style="font-weight: bold; color: #000000;">
										行业或业务
									</td>
								</tr>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="11%">
										截止日
									</td>
									<td width="25%">
										项目名称
									</td>
									<td width="17%">
										主营业务收入(万元)
									</td>
									<td width="17%">
										主营业务成本(万元)
									</td>
									<td width="18%">
										主营业务利润(万元)
									</td>
									<td width="12%">
										毛利率(%)
									</td>
								</tr>
								<s:iterator value="answer" status="st" id="info">
									<tr bgcolor="#ffffff">
										<td width="11%" align="center">
											<s:property value="endtime" />
										</td>
										<td width="25%" align="left" style="padding-left: 5px;">
											<s:property value="projectname" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="mbr" />
										</td>
										<td width="17%" align="right" style="padding-right: 5px;">
											<s:property value="cost" />
										</td>
										<td width="18%" align="right" style="padding-right: 5px;">
											<s:property value="mbp" />
										</td>
										<td width="12%" align="right" style="padding-right: 5px;">
											<s:property value="grossprofit" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>
						</div>
                      </div>
                      
                      
					</s:iterator>
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
