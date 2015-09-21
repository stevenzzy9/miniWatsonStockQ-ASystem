package com.solid.action;

import java.util.ArrayList;

import com.QA.Model.CompanyInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.CompanyHandle;

/**
 * 指定公司的详细信息
 * 
 * @author Jeremy Lee
 * 
 */
public class GetCompanyInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -590251176310665927L;

	/**
	 * tag = 0 basicInfo |tag = 1 公司高管| tag = 2 财务指标| tag = 3 分红配送| tag = 4
	 * 历史行情| tag = 5 收入构成| tag = 6 公司新闻 |
	 */

	private int tag;
	/** 公司号 */
	private int id;
	/** 公司的新闻页数序号 */
	private int pageid;
	/** 公司的新闻 */
	private String content;
	/** 公司的基本信息 */
	private CompanyInfo basicInfo;
	/** 公司的其他信息 */
	private ArrayList moreInfo;
	/** 传递过来的问题参数 */
	private String question;

	public String execute() throws Exception {
		CompanyHandle ch = new CompanyHandle();
		setBasicInfo(ch.findCompanyInfoById(id));
		switch (tag) {
		case 1:
			setMoreInfo(ch.getCompanyExecutive(id));
			break;
		case 2:
			setMoreInfo(ch.getCompanyFinance(id));
			break;
		case 3:
			setMoreInfo(ch.getCompanyBonus(id));
			break;
		case 4:
			setMoreInfo(ch.getCompanyHistory(id));
			break;
		case 5:
			setMoreInfo(ch.getCompanyIncome(id));
			break;
		case 6:
			if (pageid == 0)
				pageid = 1;
			setContent(ch.getCompanyNews(id, pageid));
		}
		return "success";
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public CompanyInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(CompanyInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	public ArrayList getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(ArrayList moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
