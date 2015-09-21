package com.QA.Model;

/**
 * 财务指标信息
 * @author li
 *
 */
public class CompanyFinance {

	
	private String time;//时间
	private String eps;//每股收益
	private String neps;//每股收益扣除
	private String navps;//每股净资产
	private String anaps;//调整后每股净资产
	private String roe;//净资产收益率
	private String scf;//每股资本公积金
	private String reps;//每股未分配利润
	private String mbr;//主营业务收入
	private String mbp;//主营业务利润
	
	public CompanyFinance(String time,String eps,String neps,String navps,String anaps,
			String roe,String scf,String reps,String mbr,String mbp)
	{
		this.time=time;
		this.eps=eps;
		this.neps=neps;
		this.navps=navps;
		this.anaps=anaps;
		this.roe=roe;
		this.scf=scf;
		this.reps=reps;
		this.mbr=mbr;
		this.mbp=mbp;
		
	}
	
	public String getTime() {
		return time;
	}
	public String getEps() {
		return eps;
	}
	public String getNeps() {
		return neps;
	}
	public String getNavps() {
		return navps;
	}
	public String getAnaps() {
		return anaps;
	}
	public String getRoe() {
		return roe;
	}
	public String getScf() {
		return scf;
	}
	public String getReps() {
		return reps;
	}
	public String getMbr() {
		return mbr;
	}
	public String getMbp() {
		return mbp;
	}
	
	@Override
	public String toString() {
		return "CompanyFinance [时间=" + time + ", 每股收益=" + eps + ", 每股收益扣除=" + neps + ", 每股净资产=" + navps + ", 调整后每股净资产=" + anaps + ", 净资产收益率=" + roe
				+ ", 每股资本公积金=" + scf + ", 每股未分配利润=" + reps + ", 主营业务收入=" + mbr + ", 主营业务利润=" + mbp + "]";
	}
	
}
