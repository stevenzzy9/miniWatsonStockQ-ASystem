package com.solid.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.QA.Util.DBUtil;

/**
 * 新闻新股数据处理类
 * 
 * @author Jeremy Lee
 *
 */
public class NewsUtilImpl {

	/** 该网站的 */
	public final static String BASE_PATH = "http://news.stockstar.com/info/";
	public final static String PAGE = "dstock.aspx/";
	public final static String CONTENT_AREA = "colnews_left";
	public final static String CONTENT_AREA1 = "Re_TabMain";
	public final static String BASE_PATH1 = "http://stock.stockstar.com/ipo/";
	public final static String PAGE1 = "data_ipo.aspx";

	private boolean flag = false;

	//公司的id,用于搜索新闻传递参数
	private int id;
	/**
	 * 
	 * @param 公司的代码
	 * @param 新闻的页数
	 * @return 公司新闻的内容（html格式）
	 */
	public String search(String code, int pageid,int id) {
		this.id = id;
		String url = BASE_PATH + PAGE + "?code=" + code + "&pageid=" + pageid;
		String content;
		content = new HtmlFilter(this.id).filter(url);
		// content = ContentFilter.filter(content);
		// content = PathFilter.filter(content);
		return content;
	}

	/**
	 * 
	 * @param 公司的名称
	 * @return 公司的代号
	 */
	public String search(String name) {
		String sql1 = "select code,id from companyinfo where abbreviation = \'"
				+ name + "\'";
		String sql2 = "select code,id from companyinfo where code = \'" + name
				+ "\'";
		String code = null;
		try {
			ResultSet rs1 = DBUtil.query(sql1, new ArrayList());
			while (rs1.next()) {
				flag = true;
				code = rs1.getString("code");
				id = rs1.getInt("id");
				// 打印
				System.out.println("根据公司名得到对应的带代号" + code);
				return code;
			}
			ResultSet rs2 = DBUtil.query(sql2, new ArrayList());
			while (rs2.next()) {
				flag = true;
				code = rs2.getString("code");
				id = rs2.getInt("id");
				// 打印
				System.out.println("找到所输入的代号" + code + "--" + name);
				return code;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * 
	 * @param 页数
	 * @return 新股内容（html格式）
	 */
	public String scanNewShare(int page) {
		String url = BASE_PATH1 + PAGE1 + "?page=" + page;
		String content = new HtmlFilter1().filter(url);
		return content;
	}

	/**
	 * 
	 * @param 页数序号
	 * @param 列数序号
	 * @param 排列方式
	 * @return 新股信息的内容（html格式）
	 */
	public String scanNewShare(int page, int col, int tag) {
		String url;
		if (col != 0) {
				url = BASE_PATH1 + PAGE1 + "?page=" + page + "&col=" + col
						+ "&tag=" + tag;
		} else
			url = BASE_PATH1 + PAGE1 + "?page=" + page;
		String content = new HtmlFilter1().filter(url);
		return content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
