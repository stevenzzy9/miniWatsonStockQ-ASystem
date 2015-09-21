package com.QA.Extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.QA.Model.AnalyResult;
import com.QA.Model.AnswerType;
import com.QA.Model.CompanyBonus;
import com.QA.Model.CompanyExecutive;
import com.QA.Model.CompanyFinance;
import com.QA.Model.CompanyHistory;
import com.QA.Model.CompanyIncome;
import com.QA.Model.InfoPosition;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.Util.DBUtil;

public class CompanyInfoExtra {

	/**
	 * 根据公司名获得 公司的分红送配
	 * @param companyName
	 * 公司名
	 * @return
	 */
	private static ArrayList<CompanyBonus> getCompanyBonus(String companyName)
	{
		ArrayList<CompanyBonus> bonuses=new ArrayList<CompanyBonus>();
		String query="select * from companybonus,companyinfo where companybonus.id=companyinfo.id and companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
		params.add(companyName);
		try {
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				CompanyBonus tmp=new CompanyBonus(res.getString("endtime"),
						res.getString("publishtime"),
						res.getString("dividentamount"),
						res.getString("bonusissue"),
						res.getString("increment"),
						res.getString("registerdate"),
						res.getString("headline"));
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
	 * @param companyName
	 * 公司名
	 * @return
	 */
	private static ArrayList<CompanyIncome> getCompanyIncome(String companyName)
	{
		ArrayList<CompanyIncome> incomes=new ArrayList<CompanyIncome>();
		String query="select * from companyincome,companyinfo where companyincome.id=companyinfo.id and companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
		params.add(companyName);
		try {
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				CompanyIncome tmp=new CompanyIncome(
						res.getString("tag"),
						res.getString("endtime"),
						res.getString("projectname"),
						res.getString("mbr"),
						res.getString("cost"),
						res.getString("mbp"),
						res.getString("grossprofit"));
				
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
	 * @param companyName
	 *公司名
	 * @return
	 */
	private static ArrayList<CompanyFinance> getCompanyFinance(String companyName)
	{
		ArrayList<CompanyFinance> finances=new ArrayList<CompanyFinance>();
		String query="select * from companyfinance,companyinfo where companyfinance.id=companyinfo.id and companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
		params.add(companyName);
		try {
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				CompanyFinance tmp=new CompanyFinance(
						res.getString("time"),
						res.getString("eps"),
						res.getString("neps"),
						res.getString("navps"),
						res.getString("anaps"),
						res.getString("roe"),
						res.getString("scf"),
						res.getString("reps"),
						res.getString("mbr"),
						res.getString("mbp"));
				
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
	 * @param companyName
	 * 公司名
	 * @return
	 */
	private static ArrayList<CompanyHistory> getCompanyHistory(String companyName)
	{
		ArrayList<CompanyHistory> history=new ArrayList<CompanyHistory>();
		String query="select * from companyhistory,companyinfo where companyhistory.id=companyinfo.id and companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
		params.add(companyName);
		try {
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				CompanyHistory tmp=new CompanyHistory(
						res.getString("date"),
						res.getString("rosedrop"),
						res.getString("openingprice"),
						res.getString("closingprice"),
						res.getString("tradingvolume"),
						res.getString("turnover"),
						res.getString("highest"),
						res.getString("lowest"));
				
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
	 * @param companyName
	 * @return
	 */
	private static ArrayList<CompanyExecutive> getCompanyExecutive(String companyName)
	{
		ArrayList<CompanyExecutive> executive=new ArrayList<CompanyExecutive>();
		String query="select * from companyexecutive,companyinfo where companyexecutive.id=companyinfo.id and companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
		params.add(companyName);
		try {
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				CompanyExecutive tmp=new CompanyExecutive(
						res.getString("name"),
						res.getString("sex"),
						res.getString("position"),
						res.getString("education"),
						res.getString("salary"),
						res.getString("shares"),
						res.getString("officeholding"),
						res.getString("positionend"));
				
				executive.add(tmp);
			}		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return executive;
	}
	
	/**
	 * 根据公司名 与列名在公司基本信息表中获得指定列的信息
	 * @param companyName
	 * @param column
	 * @return
	 */
	private static String getCompanyInfo(String companyName,String column)
	{
	
		String query="select "+column+" from companyinfo where companyinfo.abbreviation=?";
		ArrayList<Object> params=new ArrayList<Object>();
	//	params.add(column);
		params.add(companyName);
		String result="";
		try {
			ResultSet res=DBUtil.query(query, params);
			res.next();
			result=res.getString(column);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
		
	}
	/**
	 * 更具公司名获得公司ID
	 * @param companyName
	 * @return
	 */
	private static int getCompanyId(String companyName)
	{
		String query="select id from companyinfo where abbreviation=?";
		ArrayList<Object>params=new ArrayList<Object>();
		params.add(companyName);
		int id=-1;
		try {
			ResultSet res=DBUtil.query(query, params);
			if(res.next())
			{
				id=res.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public static ArrayList<QuestionAnswerPair> getAnswers(ArrayList<AnalyResult> analyresults)
	{
		ArrayList<QuestionAnswerPair> answers=new ArrayList<QuestionAnswerPair>();
		for(AnalyResult result:analyresults)
		{
			if (result.getInfoPosition() != null) {
				QuestionAnswerPair answer = getAnswer(result);
				answers.add(answer);
			}else
			{
				String companyName=result.getName();
				QuestionAnswerPair answer = new QuestionAnswerPair(
						getCompanyId(companyName),
						companyName,
						"没有找到您要的答案",QuestionType.TYPE_STOCKINFO,AnswerType.TYPE_NULL,null);
				answers.add(answer);
			}
		}
		
		return answers;
	}
	private static QuestionAnswerPair getAnswer(AnalyResult analyresult)
	{
		QuestionAnswerPair answer = null;
		String companyName=analyresult.getName();
		InfoPosition infop=analyresult.getInfoPosition();
		int companyId=getCompanyId(companyName);
		String tableName=infop.getTable();
		if(tableName.equals("companyinfo"))
		{
			String tmp=CompanyInfoExtra.getCompanyInfo(companyName, infop.getColumn());
			answer=new QuestionAnswerPair(companyId, companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_STRING, tmp);			
			
		}else if(tableName.equals("companybonus"))
		{
			ArrayList<CompanyBonus>bonus=CompanyInfoExtra.getCompanyBonus(companyName);
			answer=new QuestionAnswerPair(companyId, companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_ARRAY_BONUS, bonus);
			
		}else if(tableName.equals("companyincome"))
		{
			ArrayList<CompanyIncome>income=CompanyInfoExtra.getCompanyIncome(companyName);
			answer=new QuestionAnswerPair(companyId, companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_ARRAY_INCOME, income);
			
		}else if(tableName.equals("companyfinance"))
		{
			ArrayList<CompanyFinance>finance=CompanyInfoExtra.getCompanyFinance(companyName);
			answer=new QuestionAnswerPair(companyId, companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_ARRAY_FINANCE, finance);
			
		}else if(tableName.equals("companyhistory"))
		{
			ArrayList<CompanyHistory>history=CompanyInfoExtra.getCompanyHistory(companyName);
			answer=new QuestionAnswerPair(companyId,companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_ARRAY_HISTORY, history);
			
		}else if(tableName.equals("companyexecutive"))
		{
			ArrayList<CompanyExecutive>executive=CompanyInfoExtra.getCompanyExecutive(companyName);
			answer=new QuestionAnswerPair(companyId, companyName,infop.getProperty(), QuestionType.TYPE_STOCKINFO,
					AnswerType.TYPE_ARRAY_EXECUTIVE, executive);
		}
		return answer;
	}
	
	
	
	public static void main(String args[])
	{
		
		ArrayList<CompanyBonus> bonus=CompanyInfoExtra.getCompanyBonus("浦发银行");
		ArrayList<CompanyIncome>incomes=CompanyInfoExtra.getCompanyIncome("浦发银行");
		ArrayList<CompanyFinance>finance=CompanyInfoExtra.getCompanyFinance("浦发银行");
		ArrayList<CompanyHistory>history=CompanyInfoExtra.getCompanyHistory("浦发银行");
		ArrayList<CompanyExecutive>executive=CompanyInfoExtra.getCompanyExecutive("浦发银行");
		System.out.println(CompanyInfoExtra.getCompanyInfo("浦发银行", "id"));
		int i=0;
		
		
	}
	
}
