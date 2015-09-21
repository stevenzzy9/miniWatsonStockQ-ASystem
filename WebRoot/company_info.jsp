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
		<title><s:property value="basicInfo.abbreviation" />公司主页</title>
		<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<link href="css/column.css" rel="stylesheet" type="text/css" />
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/tabs.js"></script>

	</head>

	<body>
		<center>

			<%@ include file="header.jsp"%>
			<div class="result">

				<div id="tree">

					<div id="tree_title"></div>

					<ul type="none">

						<li>
							<a href="GetCompanyInfo?tag=0&id=<s:property value="id"/>"
								<s:if test="tag==0">class="tree_current"</s:if>>公司概况</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=1&id=<s:property value="id"/>"
								<s:if test="tag==1">class="tree_current"</s:if>>公司高管</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=2&id=<s:property value="id"/>"
								<s:if test="tag==2">class="tree_current"</s:if>>财务指标</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=3&id=<s:property value="id"/>"
								<s:if test="tag==3">class="tree_current"</s:if>>分红配送</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=4&id=<s:property value="id"/>"
								<s:if test="tag==4">class="tree_current"</s:if>>历史行情</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=5&id=<s:property value="id"/>"
								<s:if test="tag==5">class="tree_current"</s:if>>收入构成</a>
						</li>

						<li>
							<a href="GetCompanyInfo?tag=6&id=<s:property value="id"/>"
								<s:if test="tag==6">class="tree_current"</s:if>>公司新闻</a>
						</li>
					</ul>
				</div>
				<s:if test="tag!=6">
					<div class="title">
						<h2>
							<s:property value="basicInfo.fullname" />
						</h2>
					</div>
				</s:if>
				<div class="words">

					<!-- tag=0 :公司概况  -->
					<s:if test="tag==0">
					<input type="hidden" id="for_tab_input" value="2" />
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr>
									<td width="17%" bgcolor="#f6f6f6">
										股票代码
									</td>
									<td width="32%" bgcolor="#ffffff">
										<s:property value="basicInfo.code" />
									</td>
									<td width="17%" bgcolor="#f6f6f6">
										股票简称
									</td>
									<td width="34%" bgcolor="#ffffff">
										<s:property value="basicInfo.abbreviation" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										公司全称
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.fullname" />
									</td>
									<td bgcolor="#f6f6f6">
										曾用名
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.usedname" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										市场类型
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.markettype" />
									</td>
									<td bgcolor="#f6f6f6">
										证券类别
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.securitiestype" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										成立日期
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.foundationdate" />
									</td>
									<td bgcolor="#f6f6f6">
										上市日期
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.listingdate" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										注册资本（万元）
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.registrationcapital" />
									</td>
									<td bgcolor="#f6f6f6">
										总经理
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.manager" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										法人代表
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.legalperson" />
									</td>
									<td bgcolor="#f6f6f6">
										董事会秘书
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.secretary" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										注册地址
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.registrationaddress" />
									</td>
									<td bgcolor="#f6f6f6">
										办公地址
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.officeaddress" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										电话
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.tel" />
									</td>
									<td bgcolor="#f6f6f6">
										传真
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.fax" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										电子邮箱
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.email" />
									</td>
									<td bgcolor="#f6f6f6">
										公司网址
									</td>
									<td bgcolor="#ffffff">
										<a class="link_blue3"
											href="<s:property value="basicInfo.internetsite"/>"> <s:property
												value="basicInfo.internetsite" /> </a>
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										所属行业
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.industry" />
									</td>
									<td bgcolor="#f6f6f6">
										所属地域
									</td>
									<td bgcolor="#ffffff">
										<s:property value="basicInfo.area" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										所属板块
									</td>
									<td colspan="3" bgcolor="#ffffff">
										<s:property value="basicInfo.field" />
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										主营业务
									</td>
									<td colspan="3" bgcolor="#ffffff">
                                    <pre>
                                    
<s:property value="basicInfo.business" />
                                    </pre>
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										公司简介
									</td>
									<td colspan="3" bgcolor="#ffffff">
                                     <pre>
                                     
<s:property value="basicInfo.introduction" />
                                     </pre>
									</td>
								</tr>
								<tr>
									<td bgcolor="#f6f6f6">
										发行证券一览
									</td>
									<td colspan="3" bgcolor="#ffffff">
                                     <pre>
                                     
<s:property value="basicInfo.issuesecurities" />
									</pre>
                                    </td>
								</tr>
							</tbody>
						</table>
					</s:if>

					<!-- tag=1 :公司高管 -->
					<s:elseif test="tag==1">
					<input type="hidden" id="for_tab_input" value="2" />
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
								<s:iterator value="moreInfo" status="st" id="info">
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
					</s:elseif>

					<!-- tag=2 :财务指标 -->
					<s:elseif test="tag==2">
					<input type="hidden" id="for_tab_input" value="2" />
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#dfe0e1">
							<tbody>
								<tr align="center" bgcolor="#f6f6f6">
									<td width="20%">&nbsp;
										
									</td>
									<s:iterator value="moreInfo" status="st" id="info">
										<td width="17%">
											<s:property value="time" />
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td width="20%" bgcolor="#f6f6f6">
										每股收益(元)
									</td>
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
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
									<s:iterator value="moreInfo" status="st" id="info">
										<td bgcolor="#ffffff" align="right"
											style="padding-right: 5px;">
											<s:property value="mbp" />
										</td>
									</s:iterator>
								</tr>
							</tbody>
						</table>
					</s:elseif>

					<!-- tag=3 :分红配送 -->
					<s:elseif test="tag==3">
					<input type="hidden" id="for_tab_input" value="2" />
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
								<s:iterator value="moreInfo" status="st" id="info">
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

					<!-- tag=4 :历史行情 -->
					<s:elseif test="tag==4">
					<input type="hidden" id="for_tab_input" value="2" />
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
								<s:iterator value="moreInfo" status="st" id="info">
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

					<!-- tag=5 :收入构成 -->
					<s:elseif test="tag==5">
					<input type="hidden" id="for_tab_input" value="2" />
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
								<s:iterator value="moreInfo" status="st" id="info0">
									<s:if test="tag==0">
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
									</s:if>
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
								<s:iterator value="moreInfo" status="st" id="info1">
									<s:if test="tag==1">
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
									</s:if>
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
								<s:iterator value="moreInfo" status="st" id="info2">
									<s:if test="tag==2">
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
									</s:if>
								</s:iterator>
							</tbody>
						</table>
					</s:elseif>

					<!-- tag=5 :公司新闻 -->
					<s:elseif test="tag==6">
					<input type="hidden" id="for_tab_input" value="5" />
						<s:property value="content" escape="false" />
					</s:elseif>
				</div>
			</div>

			<%@ include file="footer.jsp"%>
		</center>
	</body>
</html>
