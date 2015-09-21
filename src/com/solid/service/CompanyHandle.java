package com.solid.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.QA.Model.CompanyBonus;
import com.QA.Model.CompanyExecutive;
import com.QA.Model.CompanyFinance;
import com.QA.Model.CompanyHistory;
import com.QA.Model.CompanyIncome;
import com.QA.Model.CompanyInfo;
import com.QA.Util.DBUtil;

/**
 * 处理公司信息的业务逻辑
 * 
 * @author Jeremy Lee
 * 
 */
public class CompanyHandle {

	/**
	 * 
	 * @param 行业
	 * @param 地域
	 * @return 公司的基本信息集
	 */
	public ArrayList<CompanyInfo> findCompanyByIndAndArea(String industry,
			String area) {
		ArrayList<CompanyInfo> companies = new ArrayList<CompanyInfo>();
		String sql = "";
		if (industry.equals("0"))
			sql = "select id,code,abbreviation,tel,internetsite from companyinfo where area like \'%"
					+ area + "%\'";
		else if (area.equals("0"))
			sql = "select id,code,abbreviation,tel,internetsite from companyinfo where industry like \'%"
					+ industry + "%\'";
		else
			sql = "select id,code,abbreviation,tel,internetsite from companyinfo where industry like \'%"
					+ industry + "%\' and area like \'%" + area + "%\'";
		try {
			ResultSet rs = DBUtil.query(sql, new ArrayList());
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String abbreviation = rs.getString("abbreviation");
				String tel = rs.getString("tel");
				String internetsite = rs.getString("internetsite");
				CompanyInfo ci = new CompanyInfo(id, code, abbreviation, tel,
						internetsite);
				companies.add(ci);
			}
			return companies;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return companies;
	}

	/**
	 * 根据公司id得到公司详细信息
	 * 
	 * @param 公司id
	 * @return公司详细信息
	 */
	public CompanyInfo findCompanyInfoById(int id) {
		CompanyInfo ci = new CompanyInfo();
		String sql = "select * from companyinfo where id = " + id;
		try {
			ResultSet rs = DBUtil.query(sql, new ArrayList());
			while (rs.next()) {
				String code = rs.getString("code");
				String abbreviation = rs.getString("abbreviation");
				String fullname = rs.getString("fullname");
				String usedname = rs.getString("usedname");
				String markettype = rs.getString("markettype");
				String securitiestype = rs.getString("securitiestype");
				String foundationdate = rs.getString("foundationdate");
				String listingdate = rs.getString("listingdate");
				String registrationcapital = rs
						.getString("registrationcapital");
				String manager = rs.getString("manager");
				String legalperson = rs.getString("legalperson");
				String secretary = rs.getString("secretary");
				String registrationaddress = rs
						.getString("registrationaddress");
				String officeaddress = rs.getString("officeaddress");

				String tel = rs.getString("tel");
				String fax = rs.getString("fax");
				String email = rs.getString("email");
				String internetsite = rs.getString("internetsite");
				String industry = rs.getString("industry");
				String area = rs.getString("area");
				String field = rs.getString("field");
				String business = rs.getString("business");
				String introduction = rs.getString("introduction");
				String issuesecurities = rs.getString("issuesecurities");
				ci = new CompanyInfo(id, code, abbreviation, fullname,
						usedname, markettype, securitiestype, foundationdate,
						listingdate, registrationcapital, manager, legalperson,
						secretary, registrationaddress, officeaddress, tel,
						fax, email, internetsite, industry, area, field,
						business, introduction, issuesecurities);
				break;
			}
			return ci;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ci;
	}

	/**
	 * 根据公司名获得 公司的分红送配
	 * 
	 * @param 公司id
	 * @return 公司分红送配
	 */
	public ArrayList<CompanyBonus> getCompanyBonus(int id) {
		ArrayList<CompanyBonus> bonuses = new ArrayList<CompanyBonus>();
		String query = "select * from companybonus,companyinfo where companybonus.id=companyinfo.id and companyinfo.id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				CompanyBonus tmp = new CompanyBonus(res.getString("endtime"),
						res.getString("publishtime"), res
								.getString("dividentamount"), res
								.getString("bonusissue"), res
								.getString("increment"), res
								.getString("registerdate"), res
								.getString("headline"));
				bonuses.add(tmp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bonuses;
	}

	/**
	 * 根据公司名称获得公司的收入构成
	 * 
	 * @param 公司id
	 * @return 公司的收入构成信息
	 */
	public ArrayList<CompanyIncome> getCompanyIncome(int id) {
		ArrayList<CompanyIncome> incomes = new ArrayList<CompanyIncome>();
		String query = "select * from companyincome,companyinfo where companyincome.id=companyinfo.id and companyinfo.id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				CompanyIncome tmp = new CompanyIncome(res.getString("tag"), res
						.getString("endtime"), res.getString("projectname"),
						res.getString("mbr"), res.getString("cost"), res
								.getString("mbp"), res.getString("grossprofit"));

				incomes.add(tmp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return incomes;
	}

	/**
	 * 根据公司名获得公司财务指标
	 * 
	 * @param 公司id
	 * @return 财务指标信息
	 */
	public ArrayList<CompanyFinance> getCompanyFinance(int id) {
		ArrayList<CompanyFinance> finances = new ArrayList<CompanyFinance>();
		String query = "select * from companyfinance,companyinfo where companyfinance.id=companyinfo.id and companyinfo.id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				CompanyFinance tmp = new CompanyFinance(res.getString("time"),
						res.getString("eps"), res.getString("neps"), res
								.getString("navps"), res.getString("anaps"),
						res.getString("roe"), res.getString("scf"), res
								.getString("reps"), res.getString("mbr"), res
								.getString("mbp"));

				finances.add(tmp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finances;
	}

	/**
	 * 根据公司名称获得公司历史行情信息
	 * 
	 * @param 公司id
	 * @return 公司的历史行情信息
	 */
	public ArrayList<CompanyHistory> getCompanyHistory(int id) {
		ArrayList<CompanyHistory> history = new ArrayList<CompanyHistory>();
		String query = "select * from companyhistory,companyinfo where companyhistory.id=companyinfo.id and companyinfo.id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				CompanyHistory tmp = new CompanyHistory(res.getString("date"),
						res.getString("rosedrop"), res
								.getString("openingprice"), res
								.getString("closingprice"), res
								.getString("tradingvolume"), res
								.getString("turnover"), res
								.getString("highest"), res.getString("lowest"));

				history.add(tmp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return history;
	}

	/**
	 * 根据公司名获得公司高管信息
	 * 
	 * @param 公司id
	 * @return 公司的高管信息
	 */
	public ArrayList<CompanyExecutive> getCompanyExecutive(int id) {
		ArrayList<CompanyExecutive> executive = new ArrayList<CompanyExecutive>();
		String query = "select * from companyexecutive,companyinfo where companyexecutive.id=companyinfo.id and companyinfo.id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				CompanyExecutive tmp = new CompanyExecutive(res
						.getString("name"), res.getString("sex"), res
						.getString("position"), res.getString("education"), res
						.getString("salary"), res.getString("shares"), res
						.getString("officeholding"), res
						.getString("positionend"));

				executive.add(tmp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return executive;
	}

	public String getCompanyNews(int id, int pageid) {
		String sql = "select code from companyinfo where id = " + id;
		String code = "";
		String content;
		ResultSet rs;
		try {
			rs = DBUtil.query(sql, new ArrayList());
			while (rs.next()) {
				code = rs.getString("code");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		NewsUtilImpl util = new NewsUtilImpl();
		content = util.search(code, pageid, id);
		return content;
	}
}
