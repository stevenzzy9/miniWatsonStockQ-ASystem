package com.QA.Model;

/**
 * 分红送配
 * @author li
 *
 */
public class CompanyBonus {

	
	
	private String endtime; //截止日
	private String publishtime;  //公告日
	private String dividentamount; //分红额
	private String bonusissue; //送股
	private String increment; //转增
	private String registerdate; //股权登记日
	private String headline;  //除权除息日
	
	public CompanyBonus(String endtime,String publishtime,String dividentamount,
			String bonusissue ,String increment,String registerdate,String headline)
	{
		this.endtime=endtime;
		this.publishtime=publishtime;
		this.dividentamount=dividentamount;
		this.bonusissue=bonusissue;
		this.increment=increment;
		this.registerdate=registerdate;
		this.headline=headline;
	}
	
	
	
	public String getEndtime() {
		return endtime;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public String getDividentamount() {
		return dividentamount;
	}
	public String getBonusissue() {
		return bonusissue;
	}
	public String getIncrement() {
		return increment;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public String getHeadline() {
		return headline;
	}
	
	@Override
	public String toString() {
		return "CompanyBonus [截止日=" + endtime + ", 公告日=" + publishtime + ", 分红额=" + dividentamount + ", 送股=" + bonusissue
				+ ", 转赠=" + increment + ", 股权登记日=" + registerdate + ", 除权除息日=" + headline + "]";
	}
}
