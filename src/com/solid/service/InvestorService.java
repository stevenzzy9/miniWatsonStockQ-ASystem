package com.solid.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.QA.Model.Investor;
import com.QA.Util.DBUtil;

/**
 * 处理投资家信息
 * @author Jeremy Lee
 *
 */
public class InvestorService {
	/**
	 * 
	 * @param 投资家名字
	 * @return 投资家详细信息对象
	 */
	public Investor getInvestor(String name) {
		Investor in = null;
		String sql = "select * from investor where investor = \'" + name + "\'";
		try {
			ResultSet rs = DBUtil.query(sql, new ArrayList());
			while (rs.next()) {
				in = new Investor(rs.getInt("id"), rs.getString("investor"),
						rs.getString("introduction"), rs.getString("style"), rs
								.getString("saying"), rs.getString("event"), rs
								.getString("books"), rs.getString("field"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return in;
	}
}
