package com.solid.action;

import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.NewsUtilImpl;

/**
 * 搜索上市公司新闻
 * 
 * @author Jeremy Lee
 *
 */
public class SearchNews extends ActionSupport {

	/**
	 * company为用户输入的公司名或者公司代号
	 * code为返回的公司代号
	 */
	private static final long serialVersionUID = 1L;
	private String company;
	private String code;
	/**公司在数据库中对应的id*/
	private int id;

	public String execute() throws Exception {
		System.out.println(company);
		NewsUtilImpl util = new NewsUtilImpl();
		code = util.search(company);
		id = util.getId();
		System.out.println(code);
		System.out.println(id);
		if (code != null)
			return "success";
		return "failed";
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
