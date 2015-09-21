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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>SolidQA</title>
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/tabs.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
	$("#bk_dylist").val("<s:property value="area" escape="false" />");
	$("#bk_hylist").val("<s:property value="industry" escape="false" />");
		$("#bk_dylist").bind("change", function() {
			$("#d_h").submit();
		});
		$("#bk_hylist").bind("change", function() {
			$("#d_h").submit();
		});
	});

</script>
	</head>
	<body>
		<center>
			<%@ include file="header.jsp"%>
			<input type="hidden" id="for_tab_input" value="2" />
			<div class="result">
				<div id="link">
					<a><h1>
							地域行情
						</h1> </a>
				</div>
				<div class="title_100">
					<form id="d_h" action="GetCompanys">
						<label>
							<select name="industry" id="bk_hylist" class="select_a"
								onchange="submit_dh" style="visibility: visible;">
								<option value="0">
									行业
								</option>
								<option value="金融、保险业">
									金融、保险业
								</option>
								<option value="银行业">
									&nbsp;&nbsp;银行业
								</option>
								<option value="保险业">
									&nbsp;&nbsp;保险业
								</option>
								<option value="证券、期货业">
									&nbsp;&nbsp;证券、期货业
								</option>
								<option value="金融信托业">
									&nbsp;&nbsp;金融信托业
								</option>
								<option value="基金业">
									&nbsp;&nbsp;基金业
								</option>
								<option value="其他金融业">
									&nbsp;&nbsp;其他金融业
								</option>
								<option value="房地产业">
									房地产业
								</option>
								<option value="房地产开发与经营业">
									&nbsp;&nbsp;房地产开发与经营业
								</option>
								<option value="房地产管理业">
									&nbsp;&nbsp;房地产管理业
								</option>
								<option value="房地产中介服务业">
									&nbsp;&nbsp;房地产中介服务业
								</option>
								<option value="社会服务业">
									社会服务业
								</option>
								<option value="公共设施服务业">
									&nbsp;&nbsp;公共设施服务业
								</option>
								<option value="邮政服务业">
									&nbsp;&nbsp;邮政服务业
								</option>
								<option value="专业、科研服务业">
									&nbsp;&nbsp;专业、科研服务业
								</option>
								<option value="餐饮业">
									&nbsp;&nbsp;餐饮业
								</option>
								<option value="旅馆业">
									&nbsp;&nbsp;旅馆业
								</option>
								<option value="旅游业">
									&nbsp;&nbsp;旅游业
								</option>
								<option value="娱乐服务业">
									&nbsp;&nbsp;娱乐服务业
								</option>
								<option value="卫生、保健、护理服务业">
									&nbsp;&nbsp;卫生、保健、护理服务业
								</option>
								<option value="租赁服务业">
									&nbsp;&nbsp;租赁服务业
								</option>
								<option value="其他社会服务业">
									&nbsp;&nbsp;其他社会服务业
								</option>
								<option value="农、林、牧、渔业">
									农、林、牧、渔业
								</option>
								<option value="采掘业">
									采掘业
								</option>
								<option value="煤炭采选业">
									&nbsp;&nbsp;煤炭采选业
								</option>
								<option value="石油和天然气开采业">
									&nbsp;&nbsp;石油和天然气开采业
								</option>
								<option value="黑色金属矿采选业">
									&nbsp;&nbsp;黑色金属矿采选业
								</option>
								<option value="有色金属矿采选业">
									&nbsp;&nbsp;有色金属矿采选业
								</option>
								<option value="非金属矿采选业">
									&nbsp;&nbsp;非金属矿采选业
								</option>
								<option value="其他矿采选业">
									&nbsp;&nbsp;其他矿采选业
								</option>
								<option value="采掘服务业">
									&nbsp;&nbsp;采掘服务业
								</option>
								<option value="制造业">
									制造业
								</option>
								<option value="食品、饮料">
									&nbsp;&nbsp;食品、饮料
								</option>
								<option value="纺织、服装、皮毛">
									&nbsp;&nbsp;纺织、服装、皮毛
								</option>
								<option value="木材、家具">
									&nbsp;&nbsp;木材、家具
								</option>
								<option value="造纸、印刷">
									&nbsp;&nbsp;造纸、印刷
								</option>
								<option value="石油、化学、塑胶、塑料">
									&nbsp;&nbsp;石油、化学、塑胶、塑料
								</option>
								<option value="电子">
									&nbsp;&nbsp;电子
								</option>
								<option value="金属、非金属">
									&nbsp;&nbsp;金属、非金属
								</option>
								<option value="机械、设备、仪表">
									&nbsp;&nbsp;机械、设备、仪表
								</option>
								<option value="医药、生物制品">
									&nbsp;&nbsp;医药、生物制品
								</option>
								<option value="其他制造业">
									&nbsp;&nbsp;其他制造业
								</option>
								<option value="综合类">
									综合类
								</option>
								<option value="传播与文化产业">
									传播与文化产业
								</option>
								<option value="艺术业">
									&nbsp;&nbsp;艺术业
								</option>
								<option value="信息传播服务业">
									&nbsp;&nbsp;信息传播服务业
								</option>
								<option value="其他传播、文化产业">
									&nbsp;&nbsp;其他传播、文化产业
								</option>
								<option value="出版业">
									&nbsp;&nbsp;出版业
								</option>
								<option value="声像业">
									&nbsp;&nbsp;声像业
								</option>
								<option value="广播电影电视业">
									&nbsp;&nbsp;广播电影电视业
								</option>
								<option value="电力、煤气及水的生产和供应业">
									电力、煤气及水的生产和供应业
								</option>
								<option value="电力、蒸汽、热水的生产和供应业">
									&nbsp;&nbsp;电力、蒸汽、热水的生产和供应业
								</option>
								<option value="煤气生产和供应业">
									&nbsp;&nbsp;煤气生产和供应业
								</option>
								<option value="自来水的生产和供应业">
									&nbsp;&nbsp;自来水的生产和供应业
								</option>
								<option value="建筑业">
									建筑业
								</option>
								<option value="土木工程建筑业">
									&nbsp;&nbsp;土木工程建筑业
								</option>
								<option value="装修装饰业">
									&nbsp;&nbsp;装修装饰业
								</option>
								<option value="交通运输、仓储业">
									交通运输、仓储业
								</option>
								<option value="铁路运输业">
									&nbsp;&nbsp;铁路运输业
								</option>
								<option value="公路运输业">
									&nbsp;&nbsp;公路运输业
								</option>
								<option value="管道运输业">
									&nbsp;&nbsp;管道运输业
								</option>
								<option value="水上运输业">
									&nbsp;&nbsp;水上运输业
								</option>
								<option value="航空运输业">
									&nbsp;&nbsp;航空运输业
								</option>
								<option value="交通运输辅助业">
									&nbsp;&nbsp;交通运输辅助业
								</option>
								<option value="其他交通运输业">
									&nbsp;&nbsp;其他交通运输业
								</option>
								<option value="仓储业">
									&nbsp;&nbsp;仓储业
								</option>
								<option value="信息技术业">
									信息技术业
								</option>
								<option value="通信及相关设备制造业">
									&nbsp;&nbsp;通信及相关设备制造业
								</option>
								<option value="计算机及相关设备制造业">
									&nbsp;&nbsp;计算机及相关设备制造业
								</option>
								<option value="通信服务业">
									&nbsp;&nbsp;通信服务业
								</option>
								<option value="计算机应用服务业">
									&nbsp;&nbsp;计算机应用服务业
								</option>
								<option value="批发和零售贸易">
									批发和零售贸易
								</option>
								<option value="商业经纪与代理业">
									&nbsp;&nbsp;商业经纪与代理业
								</option>
								<option value="能源、材料和机械电子设备批发业">
									&nbsp;&nbsp;能源、材料和机械电子设备批发业
								</option>
								<option value="其他批发业">
									&nbsp;&nbsp;其他批发业
								</option>
								<option value="零售业">
									&nbsp;&nbsp;零售业
								</option>
								<option value="食品、饮料、烟草和家庭用品批发业">
									&nbsp;&nbsp;食品、饮料、烟草和家庭用品批发业
								</option>

							</select>
						</label>
						&nbsp;&nbsp;

						<label>
							<select name="area" id="bk_dylist" class="select_a"
								onchange="submit_dh" style="visibility: visible;">
								<option value="0">
									地域
								</option>
								<option value="北京市">
									北京市
								</option>
								<option value="天津市">
									天津市
								</option>
								<option value="河北省">
									河北省
								</option>
								<option value="山西省">
									山西省
								</option>
								<option value="内蒙古自治区">
									内蒙古自治区
								</option>
								<option value="辽宁省">
									辽宁省
								</option>
								<option value="吉林省">
									吉林省
								</option>
								<option value="黑龙江省">
									黑龙江省
								</option>
								<option value="上海市">
									上海市
								</option>
								<option value="江苏省">
									江苏省
								</option>
								<option value="浙江省">
									浙江省
								</option>
								<option value="安徽省">
									安徽省
								</option>
								<option value="福建省">
									福建省
								</option>
								<option value="江西省">
									江西省
								</option>
								<option value="山东省">
									山东省
								</option>
								<option value="河南省">
									河南省
								</option>
								<option value="湖北省">
									湖北省
								</option>
								<option value="湖南省">
									湖南省
								</option>
								<option value="广东省">
									广东省
								</option>
								<option value="广西壮族自治区">
									广西壮族自治区
								</option>
								<option value="海南省">
									海南省
								</option>
								<option value="重庆市">
									重庆市
								</option>
								<option value="四川省">
									四川省
								</option>
								<option value="贵州省">
									贵州省
								</option>
								<option value="云南省">
									云南省
								</option>
								<option value="西藏自治区">
									西藏自治区
								</option>
								<option value="陕西省">
									陕西省
								</option>
								<option value="甘肃省">
									甘肃省
								</option>
								<option value="青海省">
									青海省
								</option>
								<option value="宁夏回族自治区">
									宁夏回族自治区
								</option>
								<option value="新疆维吾尔自治区">
									新疆维吾尔自治区
								</option>

							</select>
						</label>
					</form>
				</div>

				<div class="words_more">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#dfe0e1">
						<tbody>
							<tr align="center" bgcolor="#f6f6f6">
								<td width="14%">
									公司名
								</td>
								<td width="15%">
									公司代号
								</td>
								<td width="15%">
									电话
								</td>
								<td width="14%">
									网址
								</td>
							</tr>

							<s:iterator value="companies" status="st" id="com">
								<tr bgcolor="#ffffff"
									onmouseout=" this.style.backgroundColor='#FFFFFF';"
									onmouseover=" this.style.backgroundColor='#fcfae7';"
									style="background-color: rgb(255, 255, 255);">
									<td width="14%" align="center">
										<a href="GetCompanyInfo?id=<s:property value="id" />" target="_blank" ><s:property value="abbreviation" /></a>
									</td>
									<td width="15%" align="center">
										<s:property value="code" />
									</td>
									<td width="15%" align="center">
										<s:property value="tel" />
									</td>
									<td width="14%" align="center">
										<a href="<s:property value="internetsite" />" target="_blank"><s:property
												value="internetsite" />
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<pre>&nbsp;</pre>
				</div>
			</div>

			<%@ include file="footer.jsp"%>
		</center>
	</body>
</html>