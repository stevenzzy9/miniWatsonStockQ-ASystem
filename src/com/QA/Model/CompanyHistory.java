package com.QA.Model;

/**
 * 公司历史价格
 * @author li
 *
 */
public class CompanyHistory {


	private String date;  //日期
	private String rosedrop;  //涨跌幅
	private String openingprice; //列名
	private String closingprice;  //收盘价
	private String tradingvolume; //成交量
	private String turnover; //成交额
	private String highest; //最高价
	private String lowest; //最低价
	public CompanyHistory(String date,String rosedrop,String openingprice,String closingprice
			,String tradingvolume,String turnover,String highest,String lowest)
	{
		this.date=date;
		this.rosedrop=rosedrop;
		this.openingprice=openingprice;
		this.closingprice=closingprice;
		this.tradingvolume=tradingvolume;
		this.turnover=turnover;
		this.highest=highest;
		this.lowest=lowest;
	}
	

	public String getData() {
		return date;
	}
	public String getRosedrop() {
		return rosedrop;
	}
	public String getOpeningprice() {
		return openingprice;
	}
	public String getClosingprice() {
		return closingprice;
	}
	public String getTradingvolume() {
		return tradingvolume;
	}
	public String getTurnover() {
		return turnover;
	}
	public String getHighest() {
		return highest;
	}
	public String getLowest() {
		return lowest;
	}
	
	@Override
	public String toString() {
		return "CompanyHistory [日期=" + date + ", 涨跌幅=" + rosedrop + ", 开盘价=" + openingprice + ", 收盘价=" + closingprice + ", 成交量="
				+ tradingvolume + ", 成交额=" + turnover + ", 最高=" + highest + ", 最低=" + lowest + "]";
	}
}
