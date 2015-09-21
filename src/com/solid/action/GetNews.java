package com.solid.action;

import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.NewsUtilImpl;

/**
 * 输入公司代号，得到对应的公司新闻
 * 有2种方式会调用这个类.
 * 第一是搜索公司代号或者公司名称查询新闻
 * 第二是在公司主页界面直接查询新闻
 * @author Jeremy Lee
 *
 */
public class GetNews extends ActionSupport {
	
	/**
	 * code 为公司的代号
	 * content 为新闻的内容（html格式）
	 * pageid 为新闻的页数序号
	 * 
	 */
	private static final long serialVersionUID = 2603992653898933228L;
	
	//传入的id参数，便于之后的显示
	private int id;
	private String code;
	private String content;
	private int pageid;
	private int tag;

	public String execute() throws Exception {
		tag = 6;
		NewsUtilImpl util = new NewsUtilImpl();
		if (pageid == 0)
			pageid = 1;
		content = util.search(code, pageid,id);
		return "success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
}
