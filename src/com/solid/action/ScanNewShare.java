package com.solid.action;

import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.NewsUtilImpl;

/**
 * 浏览新股信息
 * 
 * @author Jeremy Lee
 *
 */
public class ScanNewShare extends ActionSupport {

	/**
	 * content为新股信息的内容（html格式）
	 * page为新股信息的页数序号，默认为1，即第一页
	 * col为信息选定的列数序号，默认为0，即没有选定
	 * tag为某一列排序方式，默认为-1，即没有排序
	 * 
	 */
	private static final long serialVersionUID = -8251438866325179075L;
	private String content;
	private int page;
	private int col;
	private int tag=-1;

	public String execute() throws Exception {
		if (page == 0)
			page = 1;
		NewsUtilImpl util = new NewsUtilImpl();
		content = util.scanNewShare(page,col,tag);
		if (content != null)
			return "success";
		else
			return "failed";

	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
}
