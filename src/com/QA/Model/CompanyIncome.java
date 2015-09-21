package com.QA.Model;

/**
 * 公司收入构成
 * @author li
 *
 */
public class CompanyIncome {

	
	private String tag;   //产品-地区-行业
	private String endtime;//截止日
	private String projectname;//项目名称
	private String mbr;//主营业务收入(万元)
	private String cost;//主营业务成本(万元)
	private String mbp;//主营业务利润(万元)
	private String grossprofit;//毛利率(%)
	
	public CompanyIncome(String tag,String endtime,String projectname,String mbr,String cost,String mbp,String grossprofit)
	{
		this.tag=tag;
		this.endtime=endtime;
		this.projectname=projectname;
		this.mbr=mbr;
		this.cost=cost;
		this.mbp=mbp;
		this.grossprofit=grossprofit;
		
	}
	
	public String getTag() {
		return tag;
	}
	public String getEndtime() {
		return endtime;
	}
	public String getProjectname() {
		return projectname;
	}
	public String getMbr() {
		return mbr;
	}
	public String getCost() {
		return cost;
	}
	public String getMbp() {
		return mbp;
	}
	public String getGrossprofit() {
		return grossprofit;
	}
	@Override
	public String toString() {
		return "CompanyIncome [tag=" + tag + ", 截止日=" + endtime + ", 项目名称=" + projectname + ", 主营业务收入（万元）=" + mbr + ", 主营业务成本=" + cost
				+ ", 主营业务利润(万元)=" + mbp + ", 毛利率(%)=" + grossprofit + "]";
	}
	
}
