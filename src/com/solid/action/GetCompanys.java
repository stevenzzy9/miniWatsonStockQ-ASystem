package com.solid.action;

import java.util.ArrayList;

import com.QA.Model.CompanyInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.CompanyHandle;

/**
 * 根据行业地域得到符合条件的公司列表
 * 
 * @author Jeremy Lee
 */

public class GetCompanys extends ActionSupport {

	private static final long serialVersionUID = 7891936360090625174L;
	/** 行业名 */
	private String industry;
	/** 地域名 */
	private String area;
	/** 符合条件的公司列表 */
	private ArrayList<CompanyInfo> companies;

	public String execute() throws Exception {
		if (industry == null || area == null || industry.equals("0") && area.equals("0"))
			return "success";
		CompanyHandle ch = new CompanyHandle();
		setCompanies(ch.findCompanyByIndAndArea(industry, area));
		return "success";
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ArrayList<CompanyInfo> getCompanies() {
		return companies;
	}

	public void setCompanies(ArrayList<CompanyInfo> companies) {
		this.companies = companies;
	}
}
